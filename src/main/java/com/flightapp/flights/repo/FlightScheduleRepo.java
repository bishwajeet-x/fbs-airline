package com.flightapp.flights.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.flights.entity.FlightSchedule;

public interface FlightScheduleRepo extends JpaRepository<FlightSchedule, String> {

	public Optional<List<FlightSchedule>> findByStatus(int status);
	public Optional<List<FlightSchedule>> findByStaAndSourceIgnoreCaseAndDestinationIgnoreCaseAndAirlineAirlineStatusId(
			Date parse, String source, String destination, int statusId);
	//public Optional<FlightSchedule> findByFlightScheduleFlightCode(String flightCode);
	public Optional<FlightSchedule> findByFlightCode(String flightCode);
	public Optional<List<FlightSchedule>> findBySourceIgnoreCaseAndDestinationIgnoreCaseAndAirlineAirlineStatusId(
			String source, String destination, int i);
}
