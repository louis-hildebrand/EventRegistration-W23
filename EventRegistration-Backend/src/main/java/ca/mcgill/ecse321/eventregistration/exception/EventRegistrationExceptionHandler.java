package ca.mcgill.ecse321.eventregistration.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EventRegistrationExceptionHandler {

	@ExceptionHandler(EventRegistrationException.class)
	public ResponseEntity<String> handleEventRegistrationException(EventRegistrationException e) {
		return new ResponseEntity<String>(e.getMessage(), e.getStatus());
	}
}
