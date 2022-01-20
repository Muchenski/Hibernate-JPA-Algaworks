package com.locadora.jpa.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.locadora.jpa.dtos.car.CarGetDTO;

public interface CarResourceContract {

	public abstract ResponseEntity<List<?>> getCarReport();

	public abstract ResponseEntity<List<?>> getLicensePlates();

	public abstract ResponseEntity<List<?>> getDailyValueAndLicensePlate();

	public abstract ResponseEntity<List<CarGetDTO>> getCarByColorEquals(String searchColor);

	public abstract ResponseEntity<List<CarGetDTO>> getCarsWithADailyRateGreaterThanOrEqualToTheAverage();

	public abstract ResponseEntity<List<CarGetDTO>> getNeverRentedCarsHibernate();

	public abstract ResponseEntity<List<?>> totalRentalValuePerCarHibernate();

	public abstract ResponseEntity<List<CarGetDTO>> getCarsOfManufacturerHibernate(String manufacturerName);

}
