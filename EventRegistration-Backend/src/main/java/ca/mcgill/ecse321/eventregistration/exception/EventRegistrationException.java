package ca.mcgill.ecse321.eventregistration.exception;

import org.springframework.http.HttpStatus;

public class EventRegistrationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	
	public EventRegistrationException(HttpStatus status, String message) {
		super(message);
		
		this.status = status;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
}
