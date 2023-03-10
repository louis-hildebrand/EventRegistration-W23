package ca.mcgill.ecse321.eventregistration.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import ca.mcgill.ecse321.eventregistration.model.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonRequestDto {

	@NotBlank(message = "Name cannot be blank.")
	private String name;
	@NotNull(message = "Password must not be null.")
	@Size(min = 10, message = "Password must be at least 10 characters long.")
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
