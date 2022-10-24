package com.flightapp.airlines.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flightapp.airlines.dto.AirlineDto;
import com.flightapp.airlines.entity.Airline;
import com.flightapp.airlines.entity.AirlineStatus;

@Service
public class AirlineServiceImpl implements AirlinesService {

	@Override
	public List<Airline> fetchAllAirlines() {
		return List.of(new Airline(1l, "INDIGO", new AirlineStatus(101, "ACTIVE")));
	}

	@Override
	public Airline createAirlines(AirlineDto request) {
		return new Airline(1l, request.getAirlineName(), new AirlineStatus(101, "ACTIVE"));
	}

	@Override
	public Airline updateAirline(AirlineDto request) {
		return new Airline(1l, request.getAirlineName(), new AirlineStatus(101, "ACTIVE"));
	}

	@Override
	public Airline fetchAirlineById(int airlineId) {
		return new Airline(1l, "INDIGO", new AirlineStatus(101, "ACTIVE"));
	}

}
