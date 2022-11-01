package com.flightapp.flights.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FlightIdGenerationKey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long flightId;
	

}
