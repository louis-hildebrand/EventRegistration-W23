package ca.mcgill.ecse321.eventregistration.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public User findUserById(int id);
	
}
