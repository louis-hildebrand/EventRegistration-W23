package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		Person john = new Person();
		john.setName(name);
		
		// Insert into DB
		john = repo.save(john);
		
		// Read from DB by ID
		Person johnFromDb = repo.findPersonById(john.getId());
		
		// Check attributes
		assertNotNull(johnFromDb);
		assertEquals(name, johnFromDb.getName());
	}
	
}
