package com.flightapp.flights.service;

import java.util.List;

import com.flightapp.flights.dto.FlightScheduleDto;
import com.flightapp.flights.dto.SearchFlightParams;
import com.flightapp.flights.entity.FlightSchedule;
import com.flightapp.flights.entity.FlightStatus;

public interface FlightScheduleService {

	public List<FlightSchedule> fetchAllFlights();
	public List<FlightSchedule> fetchFlightsByStatus(int status);
	public FlightSchedule fetchFlightByCode(String flightCode);
	public FlightSchedule changeFlightStatus(String flightCode, int status);
	public FlightSchedule createFlight(FlightScheduleDto flightSchedule);
	public FlightSchedule editFlight(FlightScheduleDto flight);
	public List<FlightSchedule> searchFlights(SearchFlightParams params);
	FlightStatus createFlightStatus(FlightStatus status);
	public FlightSchedule changeStatus(FlightScheduleDto request);
}
