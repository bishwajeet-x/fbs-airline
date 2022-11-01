package com.flightapp.airlines.service;

import java.util.List;

import com.flightapp.airlines.dto.AirlineDto;
import com.flightapp.airlines.entity.Airline;
import com.flightapp.airlines.entity.AirlineStatus;
import com.flightapp.meal.entity.MealType;

public interface AirlinesService {

	List<Airline> fetchAllAirlines();

	Airline createAirlines(AirlineDto request);
	
	Airline updateAirline(AirlineDto request);

	Airline fetchAirlineById(int airlineId);

	boolean deleteAirline(int airlineId);
	
	AirlineStatus createAirlineStatus(AirlineStatus request);

	List<MealType> getAllMealTypes();

	Airline toggleStatus(AirlineDto airline);
	

}
