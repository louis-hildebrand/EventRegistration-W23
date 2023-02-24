package ca.mcgill.ecse321.eventregistration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("hello")
	public String hello(@RequestParam String name) {
		return "Hello, " + name + "!";
	}
	
}
