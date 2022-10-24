package com.flightapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AirlinesRest {
	
	@GetMapping("/airline")
	public List<String> findAllAirlines() {
		System.out.println("Inside findAllAirlines");
		return List.of("Indigo", "Vistara");
	}
	
	@PutMapping("/airline")
	public String updateAirline(String param) {
		return "Updated to "+param;
	}

}
