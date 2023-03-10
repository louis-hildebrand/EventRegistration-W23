package ca.mcgill.ecse321.eventregistration.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.eventregistration.dto.PersonRequestDto;
import ca.mcgill.ecse321.eventregistration.dto.PersonResponseDto;
import ca.mcgill.ecse321.eventregistration.model.Person;
import ca.mcgill.ecse321.eventregistration.service.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@GetMapping("/person")
	public Iterable<PersonResponseDto> getAllPeople() {
		return StreamSupport.stream(personService.getAllPeople().spliterator(), false)
				.map(p -> new PersonResponseDto(p))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/person/{id}")
	public PersonResponseDto getPersonById(@PathVariable int id) {
		Person person = personService.getPersonById(id);
		return new PersonResponseDto(person);
	}
	
	@PostMapping("/person")
	public PersonResponseDto createPerson(@RequestBody PersonRequestDto personDto) {
		Person person = personDto.toModel();
		person = personService.createPerson(person);
		return new PersonResponseDto(person);
	}
}
