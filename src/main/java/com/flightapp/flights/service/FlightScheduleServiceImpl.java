package com.flightapp.flights.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.airlines.repo.AirlineStatusRepo;
import com.flightapp.airlines.repo.AirlinesRepo;
import com.flightapp.common.Constants;
import com.flightapp.common.Utils;
import com.flightapp.exception.IllegalInputException;
import com.flightapp.exception.NotFoundException;
import com.flightapp.exception.SomethingWentWrong;
import com.flightapp.flights.dto.FlightScheduleDto;
import com.flightapp.flights.dto.SearchFlightParams;
import com.flightapp.flights.entity.FlightClass;
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
	public FlightSchedule fetchFlightByCode(String flightCode) {
		Optional<FlightSchedule> flight = flightRepo.findByFlightCode(flightCode);
		if(flight.isEmpty()) {
			throw new NotFoundException("Flight code "+flightCode+" was not found in the DB.");
		}
		return flight.get();
	}

	@Override
	public FlightSchedule changeFlightStatus(String flightCode, int status) {
		Optional<FlightSchedule> existing = flightRepo.findByFlightCode(flightCode);
		if(existing.isEmpty()) {
			throw new NotFoundException("Flight code "+flightCode+" was not found in the DB.");
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
		newFlight.setFlightCode(flight.getFlightCode());
		newFlight.setAirline(airlineRepo.findById(flight.getAirlineId()).get());
		newFlight.setSource(flight.getSource());
		newFlight.setDestination(flight.getDestination());
		newFlight.setFlightClass(flight.getFlightClass());
		newFlight.setSta(flight.getSta());
		newFlight.setEta(flight.getEta());
		if(flight.getStatus().getId() == Constants.DELAYED.getId()) {
			newFlight.setEta(Utils.addHoursToDate(flight.getEta(), 2));
		}
		newFlight.setFlightHours(flight.getFlightHours());
		newFlight.setStatus(statusRepo.findById(flight.getStatus().getId()).get());
		return persistFlight(newFlight, "Flight created successfully");
	}

	@Override
	public FlightSchedule editFlight(FlightScheduleDto flight) {
		Optional<FlightSchedule> existing = flightRepo.findByFlightCode(flight.getFlightCode());
		if(existing.isEmpty()) {
			throw new NotFoundException("Flight code "+flight.getFlightCode()+" was not found in the DB.");
		}
		existing.get().setAirline(airlineRepo.findById(flight.getAirlineId()).get());
		existing.get().setSource(flight.getSource());
		existing.get().setDestination(flight.getDestination());
		existing.get().setFlightClass(flight.getFlightClass());
		existing.get().setSta(flight.getSta());
		existing.get().setEta(flight.getEta());
		existing.get().setStatus(statusRepo.findById(flight.getStatus().getId()).get());
		return persistFlight(existing.get(), "Flight schedule edited successfully.");
	}

	public FlightSchedule persistFlight(FlightSchedule existing, String msg) {
		try {
			return flightRepo.save(existing);
		} catch(Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrong("Could not persist flight!");
		}
	}

	@Override
	public List<FlightSchedule> searchFlights(SearchFlightParams params) {
		try {
			return flightRepo.findByStaAndSourceIgnoreCaseAndDestinationIgnoreCaseAndAirlineAirlineStatusId(
					parse.parse(params.getDate()),
					params.getSource(), params.getDestination(), 101).get();
		} catch (ParseException e) {
			throw new SomethingWentWrong("Could not find flight!");
		}
	}

	@Override
	public FlightStatus createFlightStatus(FlightStatus status) {
		System.out.println("Inside createFlightStatus");
		return statusRepo.save(status);
	}

	@Override
	public FlightSchedule changeStatus(FlightScheduleDto flight) {
		System.out.println("Inside changeStatus");
		try {
			Optional<FlightSchedule> existing = flightRepo.findByFlightCode(flight.getFlightCode());
			if(existing.isEmpty()) {
				throw new NotFoundException("Flight code "+flight.getFlightCode()+" was not found in the DB.");
			}
			FlightSchedule request = existing.get();
			if(flight.getStatus().getId() == Constants.DELAYED.getId()) {
				request.setEta(Utils.addHoursToDate(request.getEta(), 2));
			}
			request.setStatus(Constants.getFlightStatus(flight.getStatus().getId()));
			return flightRepo.save(request);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrong(e.getMessage());
		}
	}
	
	
}
