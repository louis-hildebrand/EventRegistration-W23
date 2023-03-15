package ca.mcgill.ecse321.eventregistration.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Registration {

	@EmbeddedId
	private RegistrationId id;
	
	// Every model class needs a default (no args) constructor. It can be private.
	@SuppressWarnings("unused")
	private Registration() {}

	public Registration(Event event, Person person) {
		this.id = new RegistrationId(event, person);
	}

	public RegistrationId getId() {
		return id;
	}

	public static class RegistrationId implements Serializable {

		private static final long serialVersionUID = 1L;

		@ManyToOne
		private Event event;

		@ManyToOne
		private Person person;

		// Embedded IDs also need a default (no args) constructor. It can be private.
		@SuppressWarnings("unused")
		private RegistrationId() {}

		public RegistrationId(Event event, Person person) {
			this.event = event;
			this.person = person;
		}

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

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof RegistrationId)) {
				return false;
			}

			RegistrationId r = (RegistrationId) o;
			return this.event.getId() == r.event.getId() && this.person.getId() == r.person.getId();
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(this.event, this.person);
		}

	}
}
