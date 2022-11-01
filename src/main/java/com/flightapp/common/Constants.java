package com.flightapp.common;

import com.flightapp.exception.NotFoundException;
import com.flightapp.flights.entity.FlightStatus;

public final class Constants {
	
	public static final FlightStatus ON_TIME = new FlightStatus(101, "ON-TIME");
	public static final FlightStatus DEPARTED = new FlightStatus(102, "DEPARTED");
	public static final FlightStatus DELAYED = new FlightStatus(103, "DELAYED");
	public static final FlightStatus CANCELLED = new FlightStatus(104, "CANCELLED");
	
	public static FlightStatus getFlightStatus(int id) {
		FlightStatus fs = null;
		switch(id) {
			case 101:
				fs = ON_TIME;
				break;
			case 102:
				fs = DEPARTED;
				break;
			case 103:
				fs = DELAYED;
				break;
			case 104:
				fs = CANCELLED;
				break;
			default:
				throw new NotFoundException("Flight Status not found!");
		}
		return fs;
	}

}
