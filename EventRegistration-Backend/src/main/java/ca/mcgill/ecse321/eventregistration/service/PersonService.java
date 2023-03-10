package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepo;
	
	@Transactional
	public Iterable<Person> getAllPeople() {
		return personRepo.findAll();
	}
	
	@Transactional
	public Person getPersonById(int id) {
		Person person = personRepo.findPersonById(id);
		if (person == null) {
			throw new IllegalArgumentException("Not found.");
		}

		return person;
	}
	
}
