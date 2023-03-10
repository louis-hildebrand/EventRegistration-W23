package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.model.Registration.RegistrationId;

@SpringBootTest
public class RegistrationRepositoryTests {

	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private EventRepository eventRepo;
	@Autowired
	private RegistrationRepository registrationRepo;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		registrationRepo.deleteAll();
		personRepo.deleteAll();
		eventRepo.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadRegistration() {
		// Create parent entities and save them before the registration
		String name = "John";
		String passwordHash = "abcdef123456";
		Date creationDate = Date.valueOf("2023-03-10");
		Person john = new Person();
		john.setName(name);
		john.setPasswordHash(passwordHash);
		john.setCreationDate(creationDate);
		
		john = personRepo.save(john);
		
		// TODO: remove this
		assertNotNull(john);
		assertNotEquals(0, john.getId());
		System.out.println(john);
		
		String eventName = "Midterm";
		Date date = Date.valueOf("2023-02-23");
		Time startTime = Time.valueOf("8:30:00");
		Time endTime = Time.valueOf("10:00:00");
		Event midterm = new Event();
		midterm.setName(eventName);
		midterm.setDate(date);
		midterm.setStartTime(startTime);
		midterm.setEndTime(endTime);
		
		midterm = eventRepo.save(midterm);
		
		// Create registration
		RegistrationId registrationId = new RegistrationId();
		registrationId.setEvent(midterm);
		registrationId.setPerson(john);
		Registration registration = new Registration();
		registration.setId(registrationId);
		
		// Save to DB
		registration = registrationRepo.save(registration);
		
		// Read from DB
		Registration registrationFromDb = registrationRepo.findRegistrationById(registrationId);
		
		// Assertions
		assertNotNull(registrationFromDb);
		assertNotNull(registrationFromDb.getId());
		assertEquals(registrationId, registrationFromDb.getId());
	}
	
}
