package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	// Parameters:
	// (1) Query params
	// (2) Path parameters
	// (3) Body
	@GetMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		return "Hello, " + name;
	}
	
}
