package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Event;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.model.Registration;
import ca.mcgill.ecse321.eventregistration.model.Registration.RegistrationId;

@SpringBootTest
public class TestRegistrationRepository {

	@Autowired
	PersonRepository personRepo;
	@Autowired
	EventRepository eventRepo;
	@Autowired
	RegistrationRepository registrationRepo;
	
	@AfterEach
	public void clearDatabase() {
		registrationRepo.deleteAll();
		personRepo.deleteAll();
		eventRepo.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadRegistration() {
		// Create and save "parent" entities
		String personName = "Alice";
		Person alice = new Person();
		alice.setName(personName);
		alice = personRepo.save(alice);
		
		String eventName = "TechFair";
		Date eventDate = Date.valueOf("2023-02-10");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("15:00:00");
		Event techFair = new Event();
		techFair.setName(eventName);
		techFair.setDate(eventDate);
		techFair.setStartTime(startTime);
		techFair.setEndTime(endTime);
		techFair = eventRepo.save(techFair);
		
		// Create registration
		RegistrationId registrationId = new RegistrationId();
		registrationId.setEvent(techFair);
		registrationId.setPerson(alice);
		Registration registration = new Registration();
		registration.setId(registrationId);
		
		// Save to DB
		registration = registrationRepo.save(registration);
		
		// Read from DB
		Registration registrationFromDb = registrationRepo.findRepositoryById(registrationId);
		
		// Check attributes
		assertNotNull(registrationFromDb);
		assertNotNull(registrationFromDb.getId());
		assertNotNull(registrationId.equals(registrationFromDb.getId()));
	}
	
}
