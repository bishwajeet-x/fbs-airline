package com.flightapp.airlines.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.airlines.dto.AirlineDto;
import com.flightapp.airlines.entity.Airline;
import com.flightapp.airlines.entity.AirlineStatus;
import com.flightapp.airlines.repo.AirlineStatusRepo;
import com.flightapp.airlines.repo.AirlinesRepo;
import com.flightapp.exception.NotFoundException;
import com.flightapp.exception.SomethingWentWrong;
import com.flightapp.meal.entity.MealType;
import com.flightapp.meal.repo.MealRepository;

@Service
@Transactional
public class AirlineServiceImpl implements AirlinesService {
	
	@Autowired private AirlinesRepo airlinesRepo;
	@Autowired private AirlineStatusRepo statusRepo;
	@Autowired private MealRepository mealRepo;

	@Override
	public List<Airline> fetchAllAirlines() {
		return airlinesRepo.findAll();
	}
	
	@Override
	public Airline createAirlines(AirlineDto request) {
		try {
			request.setAirlineStatus(statusRepo.findById(101).get());
			Airline airline = new Airline(request);
			if(request.isMealAvailable()) {
				List<MealType> mealTypes = new ArrayList<MealType>();
				request.getMealType().forEach(meal -> {
					mealTypes.add(mealRepo.findById(meal).get());
				});
				airline.setMealType(mealTypes);
			}
			return airlinesRepo.save(airline);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrong("Could not save airline!");
		}
	}
	
	public Object addMealtoAirline(Airline airlineId, int mealId) {
		return null;
	}

	@Override
	public Airline updateAirline(AirlineDto request) {
		try {
			Airline inDb = fetchAirlineById(Integer.valueOf(String.valueOf(request.getAirlineId())));
			if(inDb != null) {
				inDb.setAirlineName(request.getAirlineName());
				Optional<AirlineStatus> status = statusRepo.findById(request.getStatus());
				inDb.setAirlineName(request.getAirlineName());
				inDb.setNoOfSeats(request.getNoOfSeats());
				inDb.setMealAvailable(request.isMealAvailable());
				List<MealType> mealTypes = new ArrayList<MealType>();
				request.getMealType().forEach(meal -> {
					mealTypes.add(mealRepo.findById(meal).get());
				});
				inDb.setMealType(mealTypes);
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
			if(airline.isEmpty()) {
				throw new NotFoundException("Airline id "+airlineId+" not found!");
			} else {
				return airline.get();
			}
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

	@Override
	public AirlineStatus createAirlineStatus(AirlineStatus request) {
		System.out.println("Creating airline status:: "+request.toString());
		return statusRepo.save(request);
	}

	@Override
	public List<MealType> getAllMealTypes() {
		return mealRepo.findAll();
	}

	@Override
	public Airline toggleStatus(AirlineDto airline) {
		try {
			Airline inDb = fetchAirlineById(Integer.valueOf(String.valueOf(airline.getAirlineId())));
			int toStatus = airline.getStatus() == 101 ? 102 : 101;
			inDb.setAirlineStatus(statusRepo.findById(toStatus).get());
			return airlinesRepo.save(inDb);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrong("Could not change status!");
		}
		
	}

}
