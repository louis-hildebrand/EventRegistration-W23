package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.Person;

@SpringBootTest
public class TestPersonRepository {

	@Autowired
	private PersonRepository repo;
	
	@Test
	public void testPersistAndLoadPerson() {
		// Create person object
		String name = "Alice";
		Person alice = new Person();
		alice.setName(name);
		
		// Store in the database
		alice = repo.save(alice);
		int id = alice.getId();
		
		// Read from database
		Person aliceFromDb = repo.findPersonById(id);
		
		// Check that attributes are intact
		assertNotNull(aliceFromDb);
		assertEquals(name, aliceFromDb.getName());
	}
	
}
