package com.locadora.jpa.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locadora.jpa.contracts.CarModelContract;
import com.locadora.jpa.dtos.car.CarGetDTO;
import com.locadora.jpa.model.dao.CarDAO;
import com.locadora.jpa.model.domain.Car;
import com.locadora.jpa.vo.DailyValueAndLicensePlate;
import com.locadora.jpa.vo.RentalValuePerCar;

@Service
public class CarService extends GenericService<Car, Long, CarDAO> implements CarModelContract {

	public CarService(CarDAO carDAO) {
		super(carDAO, Car.class);
	}

	@Override
	public List<Object[]> getCarReport() {
		return dao.getCarReport();
	}

	@Override
	public List<String> getLicensePlates() {
		return dao.getLicensePlates();
	}

	@Override
	public List<DailyValueAndLicensePlate> getDailyValueAndLicensePlate() {
		return dao.getDailyValueAndLicensePlate();
	}
	
	@SuppressWarnings("deprecation")
	public List<DailyValueAndLicensePlate> getDailyValueAndLicensePlate2() {
		return dao.getDailyValueAndLicensePlate2();
	}
	
	@SuppressWarnings("deprecation")
	public List<Object[]> getDailyValueAndLicensePlate1() {
		return dao.getDailyValueAndLicensePlate1();
	}

	@Override
	public List<CarGetDTO> getCarByColorEquals(String searchColor) {
		return dao.getCarByColorEquals(searchColor);
	}

	@Override
	public List<CarGetDTO> getCarsWithADailyRateGreaterThanOrEqualToTheAverage() {
		return dao.getCarsWithADailyRateGreaterThanOrEqualToTheAverage();
	}

	@Override
	public List<Car> getNeverRentedCarsHibernate() {
		return dao.getNeverRentedCarsHibernate();
	}

	@Override
	public List<RentalValuePerCar> totalRentalValuePerCarHibernate() {
		return dao.totalRentalValuePerCarHibernate();
	}

	@Override
	public List<Car> getCarsOfManufacturerHibernate(String manufacturerName) {
		return dao.getCarsOfManufacturerHibernate(manufacturerName);
	}

}
