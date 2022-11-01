package com.flightapp.flights.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "flight_class")
public class FlightClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long classId;
	
	private String name;
	private double fare;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	
	@Override
	public String toString() {
		return "FlightClass [name=" + name + ", fare=" + fare + "]";
	}
}
