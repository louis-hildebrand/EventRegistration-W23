package ca.mcgill.ecse321.eventregistration.repository;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.eventregistration.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	public Person findPersonById(int id);
	
}
