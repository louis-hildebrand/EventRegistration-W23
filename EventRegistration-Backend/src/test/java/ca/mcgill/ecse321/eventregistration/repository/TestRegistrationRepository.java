package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

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
	private EventRepository eventRepo;
	@Autowired
	private PersonRepository personRepo;
	@Autowired
	private RegistrationRepository registrationRepo;

	@Test
	public void testPersistAndLoadRegistration() {
		// Create registration (must first save parents)
		Person alice = new Person();
		alice.setName("Alice");
		alice = personRepo.save(alice);
		
		Event techFair = new Event();
		techFair.setName("TechFair");
		techFair.setDate(Date.valueOf("2023-02-10"));
		techFair.setStartTime(Time.valueOf("10:00:00"));
		techFair.setEndTime(Time.valueOf("15:00:00"));
		techFair = eventRepo.save(techFair);
		
		RegistrationId registrationId = new RegistrationId();
		registrationId.setEvent(techFair);
		registrationId.setPerson(alice);
		Registration registration = new Registration();
		registration.setId(registrationId);

		// Save registration (must first save parents)
		registrationRepo.save(registration);

		// Read registration from database
		Registration registrationFromDb = registrationRepo.findRegistrationById(registrationId);

		// Check attributes and associations
		assertNotNull(registrationFromDb);
		assertNotNull(registrationFromDb.getId());
		assertNotNull(registrationFromDb.getId().getPerson());
		assertEquals(alice.getId(), registrationFromDb.getId().getPerson().getId());
		assertNotNull(registrationFromDb.getId().getEvent());
		assertEquals(techFair.getId(), registrationFromDb.getId().getEvent().getId());
	}

}
