package ca.mcgill.ecse321.eventregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.eventregistration.exception.EventRegistrationException;
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
			throw new EventRegistrationException(HttpStatus.NOT_FOUND, "Not found.");
		}

		return person;
	}
	
	@Transactional
	public Person createPerson(Person person) {
		if ("C3PO".equals(person.getName())) {
			throw new EventRegistrationException(HttpStatus.BAD_REQUEST, "Droids are not allowed. We don't serve their kind.");
		}
		return personRepo.save(person);
	}
}
