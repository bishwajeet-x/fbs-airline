package com.flightapp.flights.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.airlines.repo.AirlineStatusRepo;
import com.flightapp.airlines.repo.AirlinesRepo;
import com.flightapp.exception.IllegalInputException;
import com.flightapp.exception.NotFoundException;
import com.flightapp.exception.SomethingWentWrong;
import com.flightapp.flights.dto.FlightScheduleDto;
import com.flightapp.flights.dto.SearchFlightParams;
import com.flightapp.flights.entity.FlightSchedule;
import com.flightapp.flights.entity.FlightStatus;
import com.flightapp.flights.repo.FlightScheduleRepo;
import com.flightapp.flights.repo.FlightStatusRepo;

@Service
public class FlightScheduleServiceImpl implements FlightScheduleService {
	
	@Autowired private FlightScheduleRepo flightRepo;
	@Autowired private FlightStatusRepo statusRepo;
	@Autowired private AirlinesRepo airlineRepo;
	@Autowired private AirlineStatusRepo airlineStatusRepo;
	
	SimpleDateFormat parse = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public List<FlightSchedule> fetchAllFlights() {
		return flightRepo.findAll();
	}

	@Override
	public List<FlightSchedule> fetchFlightsByStatus(int status) {
		Optional<List<FlightSchedule>> flights = flightRepo.findByStatus(status);
		return flights.get();
	}

	@Override
	public FlightSchedule fetchFlightById(long flightId) {
		Optional<FlightSchedule> flight = flightRepo.findByFlightId(flightId);
		if(flight.isEmpty()) {
			throw new NotFoundException("Flight id "+flightId+" was not found in the DB.");
		}
		return flight.get();
	}

	@Override
	public FlightSchedule changeFlightStatus(long flightId, int status) {
		Optional<FlightSchedule> existing = flightRepo.findByFlightId(flightId);
		if(existing.isEmpty()) {
			throw new NotFoundException("Flight id "+flightId+" was not found in the DB.");
		}
		
		Optional<FlightStatus> fStatus = statusRepo.findById(status);
		if(fStatus.isEmpty()) {
			throw new IllegalInputException("Invalid status code "+status);
		}
		
		existing.get().setStatus(statusRepo.findById(status).get());
		return persistFlight(existing.get(), "Flight status changed to "+status);
	}

	@Override
	public FlightSchedule createFlight(FlightScheduleDto flight) {
		FlightSchedule newFlight = new FlightSchedule();
		System.out.println("Flight Rdeq data:: "+flight.toString());
		newFlight.setAirline(airlineRepo.findById(flight.getAirlineId()).get());
		newFlight.setSource(flight.getSource());
		newFlight.setDestination(flight.getDestination());
		newFlight.setPrice(flight.getPrice());
		try {
			newFlight.setScheduledFor(parse.parse(flight.getScheduledFor()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		newFlight.setScheduledTimeHour(flight.getScheduledTimeHour());
		newFlight.setScheduledTimeMinutes(flight.getScheduledTimeMinutes());
		newFlight.setPrice(flight.getPrice());
		newFlight.setStatus(statusRepo.findById(flight.getStatusId()).get());
		return persistFlight(newFlight, "Flight created successfully");
	}

	@Override
	public FlightSchedule editFlight(FlightScheduleDto flight) {
		Optional<FlightSchedule> existing = flightRepo.findByFlightId(flight.getFlightId());
		if(existing.isEmpty()) {
			throw new NotFoundException("Flight id "+flight.getFlightId()+" was not found in the DB.");
		}
		existing.get().setAirline(airlineRepo.findById(flight.getAirlineId()).get());
		existing.get().setSource(flight.getSource());
		existing.get().setDestination(flight.getDestination());
		existing.get().setPrice(flight.getPrice());
		try {
			existing.get().setScheduledFor(parse.parse(flight.getScheduledFor()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		existing.get().setScheduledTimeHour(flight.getScheduledTimeHour());
		existing.get().setScheduledTimeMinutes(flight.getScheduledTimeMinutes());
		existing.get().setPrice(flight.getPrice());
		existing.get().setStatus(statusRepo.findById(flight.getStatusId()).get());
		return persistFlight(existing.get(), "Flight schedule edited successfully.");
	}

	public FlightSchedule persistFlight(FlightSchedule existing, String msg) {
		try {
			return flightRepo.save(existing);
		} catch(Exception e) {
			throw new SomethingWentWrong("Could not persist flight!");
		}
	}

	@Override
	public List<FlightSchedule> searchFlights(SearchFlightParams params) {
		try {
			return flightRepo.findByScheduledForAndSourceIgnoreCaseAndDestinationIgnoreCaseAndAirlineAirlineStatusId(
					parse.parse(params.getDate()),
					params.getSource(), params.getDestination(), 101).get();
		} catch (ParseException e) {
			throw new SomethingWentWrong("Could not find flight!");
		}
	}
}
