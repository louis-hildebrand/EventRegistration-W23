package ca.mcgill.ecse321.eventregistration.controller;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<PersonResponseDto> getPersonById(@PathVariable int id) {
		Person person = personService.getPersonById(id);
		PersonResponseDto responseBody = new PersonResponseDto(person);
		return new ResponseEntity<PersonResponseDto>(responseBody, HttpStatus.OK);
	}
	
	@PostMapping("/person")
	public ResponseEntity<PersonResponseDto> createPerson(@RequestBody PersonRequestDto personDto) {
		Person person = personDto.toModel();
		person = personService.createPerson(person);
		PersonResponseDto responseBody = new PersonResponseDto(person);
		return new ResponseEntity<PersonResponseDto>(responseBody, HttpStatus.CREATED);
	}
}
