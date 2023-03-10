package ca.mcgill.ecse321.eventregistration.dto;

import java.sql.Date;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonResponseDto {

	private int id;
	private String name;
	private Date creationDate;
	
	public PersonResponseDto(Person p) {
		this.id = p.getId();
		this.name = p.getName();
		this.creationDate = p.getCreationDate();
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
}
