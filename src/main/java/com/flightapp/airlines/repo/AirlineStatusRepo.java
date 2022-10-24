package com.flightapp.airlines.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.airlines.entity.AirlineStatus;

public interface AirlineStatusRepo extends JpaRepository<AirlineStatus, Integer> {

}
