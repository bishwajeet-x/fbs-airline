package com.flightapp.airlines.dto;

import java.util.List;

import com.flightapp.airlines.entity.AirlineStatus;

public class AirlineDto {
	
	private long airlineId;
	private String airlineName;
	private int noOfSeats;
	private boolean mealAvailable;
	private List<Integer> mealType;
	private int status;
	private AirlineStatus airlineStatus;
	
	public long getAirlineId() {
		return airlineId;
	}
	public void setAirlineId(long airlineId) {
		this.airlineId = airlineId;
	}
	public String getAirlineName() {
		return airlineName;
	}
	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public boolean isMealAvailable() {
		return mealAvailable;
	}
	public void setMealAvailable(boolean mealAvailable) {
		this.mealAvailable = mealAvailable;
	}
	public List<Integer> getMealType() {
		return mealType;
	}
	public void setMealType(List<Integer> mealType) {
		this.mealType = mealType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public AirlineStatus getAirlineStatus() {
		return airlineStatus;
	}
	public void setAirlineStatus(AirlineStatus airlineStatus) {
		this.airlineStatus = airlineStatus;
	}
	
	
	
	public AirlineDto(String airlineName, int noOfSeats, boolean mealAvailable,
			int status, AirlineStatus airlineStatus) {
		this.airlineName = airlineName;
		this.noOfSeats = noOfSeats;
		this.mealAvailable = mealAvailable;
		this.status = status;
		this.airlineStatus = airlineStatus;
	}
	@Override
	public String toString() {
		return "AirlineDto [airlineId=" + airlineId + ", airlineName=" + airlineName + ", noOfSeats=" + noOfSeats
				+ ", mealAvailable=" + mealAvailable + ", mealType=" + mealType + ", status=" + status
				+ ", airlineStatus=" + airlineStatus + "]";
	}
	
}
