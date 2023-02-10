package ca.mcgill.ecse321.eventregistration.model;

import java.sql.Date;
import java.sql.Time;

public class Event {

	private int id;
	
	private String name;
	
	private Date date;
	
	private Time startTime;
	
	private Time endTime;
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Time getStartTime() {
		return startTime;
	}
	
	public Time getEndTime() {
		return endTime;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
}
