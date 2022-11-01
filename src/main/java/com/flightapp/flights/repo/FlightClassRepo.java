package com.flightapp.flights.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.flights.entity.FlightClass;

public interface FlightClassRepo extends JpaRepository<FlightClass, Integer> {

}
