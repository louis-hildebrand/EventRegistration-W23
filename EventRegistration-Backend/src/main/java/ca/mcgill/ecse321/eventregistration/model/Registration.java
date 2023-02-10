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

	public static class RegistrationId implements Serializable {

		private static final long serialVersionUID = 1L;

		@ManyToOne
		private Event event;

		@ManyToOne
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
