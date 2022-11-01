package com.flightapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.flights.dto.FlightScheduleDto;
import com.flightapp.flights.dto.SearchFlightParams;
import com.flightapp.flights.entity.FlightSchedule;
import com.flightapp.flights.service.FlightScheduleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/flights")
public class FlightsController {
	
	@Autowired private FlightScheduleService flightService;
	
	@GetMapping("/")
	public ResponseEntity<List<FlightSchedule>> fetchAllFlights() {
		System.out.println("Inside fetchAllFlights");
		return ResponseEntity.ok(flightService.fetchAllFlights());
	}

	@GetMapping("/search")
	public ResponseEntity<FlightSchedule> fetchFlightById(@RequestParam("id") String flightCode) {
		System.out.println("Inside fetchAllFlights");
		return ResponseEntity.ok(flightService.fetchFlightByCode(flightCode));
	}
	
	@PostMapping("/")
	public ResponseEntity<FlightSchedule> createFlight(@RequestBody FlightScheduleDto request) {
		System.out.println("Inside createFlight");
		return ResponseEntity.ok(flightService.createFlight(request));
	}
	
	@PutMapping("/")
	public ResponseEntity<FlightSchedule> updateFlight(@RequestBody FlightScheduleDto request) {
		System.out.println("Inside updateFlight");
		return ResponseEntity.ok(flightService.editFlight(request));
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<FlightSchedule>> searchFlights(@RequestBody SearchFlightParams request) {
		System.out.println("Inside searchFlights");
		return ResponseEntity.ok(flightService.searchFlights(request));
	}
	
	@PutMapping("/status")
	public ResponseEntity<FlightSchedule> changeStatus(@RequestBody FlightScheduleDto request) {
		System.out.println("Inside changeStatus");
		return ResponseEntity.ok(flightService.changeStatus(request));
	}

}
