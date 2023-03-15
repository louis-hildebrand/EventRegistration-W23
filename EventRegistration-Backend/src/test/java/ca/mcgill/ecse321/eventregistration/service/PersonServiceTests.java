package ca.mcgill.ecse321.eventregistration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;

@SpringBootTest
public class PersonServiceTests {

	// Tell Mockito that we want a fake person repository which exposes the same
	// interface, but does NOT actually interact with the database.
	@Mock
	private PersonRepository personRepo;

	// Autowire a Person Service, but give it the mocked person repository instead
	// of a real one.
	@InjectMocks
	private PersonService personService;

	@Test
	public void testGetPersonByValidId() {
		// Setup: tell the mock how we want it to behave
		final int id = 1;
		final String name = "John";
		final String passwordHash = "password123";
		final Date creationDate = Date.valueOf("2023-03-15");
		final Person john = new Person(name, passwordHash, creationDate);
		when(personRepo.findPersonById(id)).thenReturn(john);

		// Call the component under test
		Person output = personService.getPersonById(id);

		// Check that the output is right
		assertNotNull(output);
		assertEquals(name, output.getName());
		assertEquals(passwordHash, output.getPasswordHash());
		assertEquals(creationDate, output.getCreationDate());
		// NOTE: we didn't set an ID in the canned response because that's normally
		// never required in the production code. We could add a setter for the ID, but
		// it's simpler to just ignore it here.
	}

	@Test
	public void testGetPersonByInvalidId() {
		final int invalidId = 42;
		when(personRepo.findPersonById(invalidId)).thenReturn(null);

		EventRegistrationException e = assertThrows(EventRegistrationException.class,
				() -> personService.getPersonById(invalidId));
		assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
		assertEquals("Not found.", e.getMessage());
	}

	@Test
	public void testCreateValidPerson() {
		// Again, this test isn't 100% realistic because in practice the repo would
		// return a person with nonzero ID. That's ok.
		final String name = "John";
		final String passwordHash = "password123";
		final Date creationDate = Date.valueOf("2023-03-15");
		final Person john = new Person(name, passwordHash, creationDate);
		when(personRepo.save(john)).thenReturn(john);

		Person output = personService.createPerson(john);
		
		assertNotNull(output);
		assertEquals(name, output.getName());
		assertEquals(passwordHash, output.getPasswordHash());
		assertEquals(creationDate, output.getCreationDate());
		
		// We also want to check that the service actually saved John in the database!
		// personRepo.save() should be called exactly once
		verify(personRepo, times(1)).save(john);
	}
}
