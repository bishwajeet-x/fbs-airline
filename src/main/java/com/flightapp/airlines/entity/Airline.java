package com.flightapp.airlines.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

import com.flightapp.airlines.dto.AirlineDto;
import com.flightapp.airlines.repo.AirlineStatusRepo;
import com.flightapp.meal.entity.MealType;

@Entity
@Table(name="mst_airline")
public class Airline {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long airlineId;
	
	private String airlineName;
	private int noOfSeats;
	private boolean mealAvailable;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<MealType> mealType = new ArrayList<>();
	
	@OneToOne(fetch=FetchType.EAGER)
	private AirlineStatus airlineStatus;
	
	public Airline() {}

	public Airline(String airlineName, int noOfSeats, boolean mealAvailable,
			Collection<MealType> mealType, AirlineStatus airlineStatus) {
		this.airlineName = airlineName;
		this.noOfSeats = noOfSeats;
		this.mealAvailable = mealAvailable;
		this.mealType = mealType;
		this.airlineStatus = airlineStatus;
	}
	
	public Airline(AirlineDto dto) {
		this.airlineName = dto.getAirlineName();
		this.noOfSeats = dto.getNoOfSeats();
		this.mealAvailable = dto.isMealAvailable();
		this.airlineStatus = dto.getAirlineStatus();
	}

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

	public Collection<MealType> getMealType() {
		return mealType;
	}

	public void setMealType(Collection<MealType> mealType) {
		this.mealType = mealType;
	}

	public AirlineStatus getAirlineStatus() {
		return airlineStatus;
	}

	public void setAirlineStatus(AirlineStatus airlineStatus) {
		this.airlineStatus = airlineStatus;
	}

	@Override
	public String toString() {
		return "Airline [airlineId=" + airlineId + ", airlineName=" + airlineName + ", noOfSeats=" + noOfSeats
				+ ", mealAvailable=" + mealAvailable + ", mealType=" + mealType + ", airlineStatus=" + airlineStatus
				+ "]";
	}
}
