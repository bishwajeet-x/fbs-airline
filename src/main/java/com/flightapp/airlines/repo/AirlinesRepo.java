package com.flightapp.airlines.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.airlines.entity.Airline;

@Repository
public interface AirlinesRepo extends JpaRepository<Airline, Long> {

}
