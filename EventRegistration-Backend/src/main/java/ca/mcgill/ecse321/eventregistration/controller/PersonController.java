package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/person")
	public Iterable<Person> getAllPeople() {
		return personService.getAllPeople();
	}
	
	@GetMapping("/person/{id}")
	public Person getPersonById(@PathVariable int id) {
		// TODO: Use DTOs
		return personService.getPersonById(id);
	}
	
}
