package com.flightapp.flights.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.flightapp.airlines.entity.Airline;

@Entity
@Table(name="flight_schedule")
public class FlightSchedule {
	
	/*
	 * @OneToOne(fetch = FetchType.EAGER) private FlightIdGenerationKey flightId;
	 */
	
	@Id
	@Column(name = "flight_code", length = 20)
	private String flightCode;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Airline airline;
	
	private Date sta;
	private Date eta;
	private int flightHours;
	
	private String source;
	private String destination;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade=CascadeType.ALL)
	private Collection<FlightClass> flightClass;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToOne(cascade=CascadeType.ALL)
	private FlightStatus status;

	public String getFlightCode() {
		return flightCode;
	}

	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Date getSta() {
		return sta;
	}

	public void setSta(Date sta) {
		this.sta = sta;
	}

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}

	public int getFlightHours() {
		return flightHours;
	}

	public void setFlightHours(int flightHours) {
		this.flightHours = flightHours;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Collection<FlightClass> getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(Collection<FlightClass> flightClass) {
		this.flightClass = flightClass;
	}

	public FlightStatus getStatus() {
		return status;
	}

	public void setStatus(FlightStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FlightSchedule [flightCode=" + flightCode + ", airline=" + airline + ", sta=" + sta + ", eta=" + eta
				+ ", flightHours=" + flightHours + ", source=" + source + ", destination=" + destination
				+ ", flightClass=" + flightClass + ", status=" + status + "]";
	}
}
