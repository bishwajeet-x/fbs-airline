package com.flightapp.airlines.service;

import java.util.List;

import com.flightapp.airlines.dto.AirlineDto;
import com.flightapp.airlines.entity.Airline;

public interface AirlinesService {

	List<Airline> fetchAllAirlines();

	Airline createAirlines(AirlineDto request);

	Airline updateAirline(AirlineDto request);

	Airline fetchAirlineById(int airlineId);
	

}
