package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Person;

@SpringBootTest
public class PersonRepositoryTests {

	@Autowired
	private PersonRepository repo;
	
	@BeforeEach
	@AfterEach
	public void clearDatabase() {
		repo.deleteAll();
	}
	
	@Test
	public void persistAndLoadPerson() {
		// Create Person
		String name = "John";
		String passwordHash = "abcdef123456";
		Date creationDate = Date.valueOf("2023-03-10");
		Person john = new Person();
		john.setName(name);
		john.setPasswordHash(passwordHash);
		john.setCreationDate(creationDate);
		
		// Insert into DB
		john = repo.save(john);
		
		// Read from DB by ID
		Person johnFromDb = repo.findPersonById(john.getId());
		
		// Check attributes
		assertNotNull(johnFromDb);
		assertEquals(name, johnFromDb.getName());
		assertEquals(passwordHash, johnFromDb.getPasswordHash());
		assertEquals(creationDate, johnFromDb.getCreationDate());
	}
	
}
