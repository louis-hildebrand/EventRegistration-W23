package ca.mcgill.ecse321.eventregistration.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import ca.mcgill.ecse321.eventregistration.model.Person;

public class PersonRequestDto {

	private String name;
	private String password;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Person toModel() {
		Person p = new Person();
		p.setName(name);
		// TODO: Actually hash the password
		p.setPasswordHash(password);
		p.setCreationDate(Date.valueOf(LocalDateTime.now().toLocalDate()));
		
		return p;
	}
}
