package ca.mcgill.ecse321.eventregistration.model;

public class Registration {
	
	private Event event;

	private Person person;
	
	public Event getEvent() {
		return event;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
}
