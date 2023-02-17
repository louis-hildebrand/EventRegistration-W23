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
	
	public RegistrationId getId() {
		return id;
	}
	
	public void setId(RegistrationId id) {
		this.id = id;
	}

	public static class RegistrationId implements Serializable {

		private static final long serialVersionUID = 1L;

		@ManyToOne
		private Event event;

		@ManyToOne
		private User participant;

		public Event getEvent() {
			return event;
		}

		public User getParticipant() {
			return participant;
		}

		public void setEvent(Event event) {
			this.event = event;
		}

		public void setParticipant(User participant) {
			this.participant = participant;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof RegistrationId)) {
				return false;
			}

			RegistrationId r = (RegistrationId) o;
			return this.event.getId() == r.event.getId() && this.participant.getId() == r.participant.getId();
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(this.event, this.participant);
		}

	}

}
