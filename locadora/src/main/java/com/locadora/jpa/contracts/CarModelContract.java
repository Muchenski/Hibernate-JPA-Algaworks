package com.locadora.jpa.contracts;

import java.util.List;

import com.locadora.jpa.dtos.car.CarGetDTO;
import com.locadora.jpa.model.domain.Car;
import com.locadora.jpa.vo.DailyValueAndLicensePlate;
import com.locadora.jpa.vo.RentalValuePerCar;

public interface CarModelContract {

	public abstract List<Object[]> getCarReport();

	public abstract List<String> getLicensePlates();

	public abstract List<DailyValueAndLicensePlate> getDailyValueAndLicensePlate();
	
	public abstract List<CarGetDTO> getCarByColorEquals(String searchColor);
	
	public abstract List<CarGetDTO> getCarsWithADailyRateGreaterThanOrEqualToTheAverage();

	public abstract List<Car> getNeverRentedCarsHibernate();
	
	public abstract List<RentalValuePerCar> totalRentalValuePerCarHibernate();
	
	public abstract List<Car> getCarsOfManufacturerHibernate(String manufacturerName);
	
}
