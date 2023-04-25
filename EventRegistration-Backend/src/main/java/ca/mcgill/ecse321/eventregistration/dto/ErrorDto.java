package ca.mcgill.ecse321.eventregistration.dto;

import java.util.List;

public class ErrorDto {
	private List<String> messages;
	
	// No-args constructor that Jackson can use when deserializing.
	@SuppressWarnings("unused")
	private ErrorDto() {}
	
	public ErrorDto(List<String> messages) {
		this.messages = messages;
	}
	
	public List<String> getMessages() {
		return messages;
	}
	
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
