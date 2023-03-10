package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String passwordHash;
	private Date creationDate;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
