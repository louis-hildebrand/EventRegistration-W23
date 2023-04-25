package ca.mcgill.ecse321.eventregistration.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.mcgill.ecse321.eventregistration.dto.ErrorDto;

@ControllerAdvice
public class EventRegistrationExceptionHandler {

	@ExceptionHandler(EventRegistrationException.class)
	public ResponseEntity<ErrorDto> handleEventRegistrationException(EventRegistrationException e) {
		ErrorDto dto = new ErrorDto(List.of(e.getMessage()));
		return new ResponseEntity<ErrorDto>(dto, e.getStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException e) {
		// TODO: return a custom exception object with a list of errors instead of just a string?
		List<String> messages = new ArrayList<String>();
		for (FieldError fe : e.getBindingResult().getFieldErrors()) {
			messages.add(fe.getDefaultMessage());
		}
		ErrorDto dto = new ErrorDto(messages);
		return new ResponseEntity<ErrorDto>(dto, e.getStatusCode());
	}
}
