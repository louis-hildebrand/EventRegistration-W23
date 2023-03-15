package ca.mcgill.ecse321.eventregistration.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.mcgill.ecse321.eventregistration.dto.PersonRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;
import ca.mcgill.ecse321.eventregistration.repository.RegistrationRepository;

// Start the app for real so that we can send requests to it, but use a random port to avoid conflicts.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// Reuse the same class for all the tests (instead of creating a new class each time).
@TestInstance(Lifecycle.PER_CLASS)
// Ensure the tests are run in the right order (e.g., POST before GET)
@TestMethodOrder(OrderAnnotation.class)
public class PersonIntegrationTests {

	// Stores state to be shared between tests
	private class TestFixture {
		public static final int INVALID_ID = Integer.MAX_VALUE;

		public static final String PERSON_NAME = "John";

		private int id;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}
	}

	private TestFixture fixture;
	@Autowired
	private RegistrationRepository registrationRepo;
	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private TestRestTemplate client;

	@BeforeAll
	public void setupTestFixture() {
		this.fixture = new TestFixture();
	}

	@BeforeAll
	@AfterAll
	public void clearDatabase() {
		registrationRepo.deleteAll();
		personRepo.deleteAll();
	}

	@Test
	@Order(0)
	public void testCreatePerson() {
		String name = "John";
		String password = "password123";
		PersonRequestDto request = new PersonRequestDto();
		request.setName(name);
		request.setPassword(password);

		ResponseEntity<PersonResponseDto> response = client.postForEntity("/person", request, PersonResponseDto.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(name, response.getBody().getName());
		LocalDate today = LocalDateTime.now().toLocalDate();
		assertEquals(today, response.getBody().getCreationDate());
		assertTrue(response.getBody().getId() >= 1, "Response ID is at least 1.");

		// Save the ID so that later tests can use it
		fixture.setId(response.getBody().getId());
	}

	@Test
	@Order(1)
	public void testGetPerson() {
		int id = fixture.getId();

		ResponseEntity<PersonResponseDto> response = client.getForEntity("/person/" + id, PersonResponseDto.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(TestFixture.PERSON_NAME, response.getBody().getName());
		LocalDate today = LocalDateTime.now().toLocalDate();
		assertEquals(today, response.getBody().getCreationDate());
		assertEquals(id, response.getBody().getId());
	}

	@Test
	@Order(2)
	public void testCreateInvalidPerson() {
		PersonRequestDto request = new PersonRequestDto();

		ResponseEntity<String> response = client.postForEntity("/person", request, String.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertContains("Name cannot be blank.", response.getBody());
		assertContains("Password must not be null.", response.getBody());
	}

	@Test
	@Order(3)
	public void testGetInvalidPerson() {
		ResponseEntity<String> response = client.getForEntity("/person/" + TestFixture.INVALID_ID, String.class);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Not found.", response.getBody());
	}

	private static void assertContains(String expected, String actual) {
		String assertionMessage = String.format("Error message ('%s') contains '%s'.", actual, expected);
		assertTrue(actual.contains(expected), assertionMessage);
	}
}
