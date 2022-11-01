package com.flightapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.flightapp.airlines.dto.AirlineDto;
import com.flightapp.airlines.entity.AirlineStatus;
import com.flightapp.airlines.service.AirlinesService;
import com.flightapp.common.Constants;
import com.flightapp.common.FStatus;
import com.flightapp.flights.entity.FlightStatus;
import com.flightapp.flights.service.FlightScheduleService;
import com.flightapp.meal.entity.MealType;
import com.flightapp.meal.service.MealService;

@SpringBootApplication
public class FbsAirlinesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FbsAirlinesApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(MealService service, AirlinesService airlineService, FlightScheduleService flighttService) {
		return args -> {
			service.addMealType(new MealType("Veg"));
			service.addMealType(new MealType("Non Veg"));
			
			airlineService.createAirlineStatus(new AirlineStatus(101, "ACTIVE"));
			airlineService.createAirlineStatus(new AirlineStatus(102, "INACTIVE"));
			
			airlineService.createAirlines(new AirlineDto("Air India Express", 200, false, 101, new AirlineStatus(101, "ACTIVE")));
			
			flighttService.createFlightStatus(Constants.ON_TIME);
			flighttService.createFlightStatus(Constants.DELAYED);
			flighttService.createFlightStatus(Constants.CANCELLED);
			flighttService.createFlightStatus(Constants.DEPARTED);
		};
	}

}
