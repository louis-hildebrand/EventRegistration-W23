package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Person;

@SpringBootTest
public class TestPersonRepository {

	@Autowired
	private PersonRepository repo;
	
	@AfterEach
	public void clearDatabase() {
		repo.deleteAll();
	}
	
	@Test
	public void testPersistAndLoadPerson() {
		// Create object
		String name = "Alice";
		Person alice = new Person();
		alice.setName(name);
		
		// Save to DB
		alice = repo.save(alice);
		
		// Read from DB
		Person aliceFromDb = repo.findPersonById(alice.getId());
		
		// Check attributes
		assertNotNull(aliceFromDb);
		assertEquals(name, aliceFromDb.getName());
	}
	
}
