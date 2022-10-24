package com.flightapp.airlines.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.flightapp.airlines.dto.AirlineDto;
import com.flightapp.airlines.entity.Airline;
import com.flightapp.airlines.entity.AirlineStatus;
import com.flightapp.airlines.repo.AirlineStatusRepo;
import com.flightapp.airlines.repo.AirlinesRepo;
import com.flightapp.exception.NotFoundException;
import com.flightapp.exception.SomethingWentWrong;

@Service
public class AirlineServiceImpl implements AirlinesService {
	
	@Autowired private AirlinesRepo airlinesRepo;
	@Autowired private AirlineStatusRepo statusRepo;

	@Override
	public List<Airline> fetchAllAirlines() {
		return airlinesRepo.findAll();
	}
	
	@Override
	public Airline createAirlines(AirlineDto request) {
		try {
			Optional<AirlineStatus> status = statusRepo.findById(101);
			return airlinesRepo.save(new Airline(request.getAirlineName(), status.get()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrong("Could not save airline!");
		}
	}

	@Override
	public Airline updateAirline(AirlineDto request) {
		try {
			Airline inDb = fetchAirlineById(Integer.valueOf(String.valueOf(request.getAirlineId())));
			if(inDb != null) {
				inDb.setAirlineName(request.getAirlineName());
				Optional<AirlineStatus> status = statusRepo.findById(request.getAirlineStatus());
				inDb.setAirlineStatus(status.get());
				return airlinesRepo.save(inDb);
			} else {
				throw new NotFoundException("Airline id "+request.getAirlineId()+ " not found!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrong("Could not update airline!");
		}
	}

	@Override
	public Airline fetchAirlineById(int airlineId) {
		try {
			Optional<Airline> airline = airlinesRepo.findById(Long.valueOf(airlineId));
			return airline.isEmpty() ? null : airline.get();
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotFoundException("Airline id "+airlineId+" not found!");
		}
	}

	@Override
	public boolean deleteAirline(int airlineId) {
		try {
			fetchAirlineById(airlineId);
			airlinesRepo.deleteById(Long.valueOf(airlineId));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrong("Could not delete airline!");
		}
	}

}
