package com.flightapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.airlines.dto.AirlineDto;
import com.flightapp.airlines.entity.Airline;
import com.flightapp.airlines.service.AirlinesService;


@RestController
@RequestMapping("/api/airlines")
public class AirlinesController {
	
	@Autowired private AirlinesService airlinesService;
	
	@GetMapping("/")
	public ResponseEntity<List<Airline>> fetchAllAirlines() {
		System.out.println("Inside fetchAllAirlines");
		return ResponseEntity.ok(airlinesService.fetchAllAirlines());
	}
	
	@GetMapping("/search")
	public ResponseEntity<Airline> fetchAirlineById(@RequestParam("id") int airlineId) {
		System.out.println("Inside fetchAirlineById "+airlineId);
		return ResponseEntity.ok(airlinesService.fetchAirlineById(airlineId));
	}
	
	@PostMapping("/")
	public ResponseEntity<Airline> createAirlines(@RequestBody AirlineDto request) {
		System.out.println("Inside createAirlines");
		return ResponseEntity.ok(airlinesService.createAirlines(request));
	}
	
	@PutMapping("/")
	public ResponseEntity<Airline> updateAirline(@RequestBody AirlineDto request) {
		System.out.println("Inside updateAirline");
		return ResponseEntity.ok(airlinesService.updateAirline(request));
	}
	
	@DeleteMapping("/")
	public ResponseEntity<Boolean> deleteAirline(@RequestParam("id") int airlineId) {
		System.out.println("Inside deleteAirline "+airlineId);
		return ResponseEntity.ok(airlinesService.deleteAirline(airlineId));
	}

}
