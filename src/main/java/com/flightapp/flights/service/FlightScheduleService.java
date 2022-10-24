package com.flightapp.flights.service;

import java.util.List;

import com.flightapp.flights.dto.FlightScheduleDto;
import com.flightapp.flights.dto.SearchFlightParams;
import com.flightapp.flights.entity.FlightSchedule;

public interface FlightScheduleService {

	public List<FlightSchedule> fetchAllFlights();
	public List<FlightSchedule> fetchFlightsByStatus(int status);
	public FlightSchedule fetchFlightById(long flightId);
	public FlightSchedule changeFlightStatus(long airlineId, int status);
	public FlightSchedule createFlight(FlightScheduleDto flightSchedule);
	public FlightSchedule editFlight(FlightScheduleDto flight);
	public List<FlightSchedule> searchFlights(SearchFlightParams params);
}
