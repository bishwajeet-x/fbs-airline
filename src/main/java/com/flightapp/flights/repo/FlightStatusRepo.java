package com.flightapp.flights.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.flights.entity.FlightStatus;

public interface FlightStatusRepo extends JpaRepository<FlightStatus, Integer> {

}
