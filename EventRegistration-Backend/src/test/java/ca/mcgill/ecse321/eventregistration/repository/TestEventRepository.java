package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Event;

@SpringBootTest
public class TestEventRepository {

	@Autowired
	private EventRepository repo;

	@AfterEach
	public void clearDatabase() {
		repo.deleteAll();
	}

	@Test
	public void testPersistAndLoadEvent() {
		// Create object
		String name = "TechFair";
		Date eventDate = Date.valueOf("2023-02-10");
		Time startTime = Time.valueOf("10:00:00");
		Time endTime = Time.valueOf("15:00:00");
		Event techFair = new Event();
		techFair.setName(name);
		techFair.setDate(eventDate);
		techFair.setStartTime(startTime);
		techFair.setEndTime(endTime);

		// Save to DB
		techFair = repo.save(techFair);

		// Read from DB
		Event techFairFromDb = repo.findEventById(techFair.getId());

		// Check attributes
		assertNotNull(techFairFromDb);
		assertEquals(name, techFairFromDb.getName());
		assertEquals(eventDate, techFairFromDb.getDate());
		assertEquals(startTime, techFairFromDb.getStartTime());
		assertEquals(endTime, techFairFromDb.getEndTime());
	}

}
