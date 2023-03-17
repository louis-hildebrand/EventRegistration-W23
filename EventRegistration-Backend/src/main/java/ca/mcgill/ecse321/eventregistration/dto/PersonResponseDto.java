package ca.mcgill.ecse321.eventregistration.dto;

import java.time.LocalDate;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonResponseDto {

	private int id;
	private String name;
	private LocalDate creationDate;
	
	PersonResponseDto() {}

	public PersonResponseDto(Person p) {
		this.id = p.getId();
		this.name = p.getName();
		this.creationDate = p.getCreationDate().toLocalDate();
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDate getCreationDate() {
		return creationDate;
	}
}
