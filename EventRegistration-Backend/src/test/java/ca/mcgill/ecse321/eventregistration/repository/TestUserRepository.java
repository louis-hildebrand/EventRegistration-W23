package ca.mcgill.ecse321.eventregistration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.eventregistration.model.User;

@SpringBootTest
public class TestUserRepository {

	@Autowired
	private UserRepository repo;

	@AfterEach
	public void clearDatabase() {
		repo.deleteAll();
	}

	@Test
	public void testPersistAndLoadUser() {
		// Create object
		String name = "Alice";
		User alice = new User();
		alice.setName(name);

		// Save to DB
		alice = repo.save(alice);

		// Read from DB
		User aliceFromDb = repo.findUserById(alice.getId());

		// Check attributes
		assertNotNull(aliceFromDb);
		assertEquals(name, aliceFromDb.getName());
	}

}
