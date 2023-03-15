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
	
	// Every model class needs a default (no args) constructor. It can be private.
	@SuppressWarnings("unused")
	private Person() {}

	public Person(String name, String passwordHash, Date creationDate) {
		this.name = name;
		this.passwordHash = passwordHash;
		this.creationDate = creationDate;
	}

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
}
