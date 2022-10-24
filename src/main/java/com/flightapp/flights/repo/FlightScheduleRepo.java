package com.flightapp.flights.repo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.flights.entity.FlightSchedule;

public interface FlightScheduleRepo extends JpaRepository<FlightSchedule, Long> {

	public Optional<List<FlightSchedule>> findByStatus(int status);
	public Optional<FlightSchedule> findByFlightId(long flightId);
	public Optional<List<FlightSchedule>> findByScheduledForAndSourceIgnoreCaseAndDestinationIgnoreCaseAndAirlineAirlineStatusId(
			Date parse, String source, String destination, int statusId);
}
