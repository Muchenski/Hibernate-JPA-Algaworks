package com.locadora.jpa.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.jpa.contracts.CarResourceContract;
import com.locadora.jpa.converter.CarConverter;
import com.locadora.jpa.dtos.car.CarGetDTO;
import com.locadora.jpa.dtos.car.CarNewDTO;
import com.locadora.jpa.dtos.car.CarUpdateDTO;
import com.locadora.jpa.model.domain.Car;
import com.locadora.jpa.model.service.CarService;

@RestController
@RequestMapping(value = "/cars")
public class CarResource extends GenericResource<Car, Long, CarService, CarGetDTO, CarNewDTO, CarUpdateDTO>
		implements CarResourceContract {

	public CarResource(CarService service, CarConverter converter) {
		super(service, converter);
	}

	@GetMapping(value = "/report")
	@Override
	public ResponseEntity<List<?>> getCarReport() {
		List<Object[]> results = service.getCarReport();
		return responseEntityList(results);
	}

	@GetMapping(value = "/license-plates")
	@Override
	public ResponseEntity<List<?>> getLicensePlates() {
		List<String> results = service.getLicensePlates();
		return responseEntityList(results);
	}

	@GetMapping(value = "/get-daily-value-and-license-plate")
	@Override
	public ResponseEntity<List<?>> getDailyValueAndLicensePlate() {
		return responseEntityList(service.getDailyValueAndLicensePlate());
	}
	
	@GetMapping(value = "/get-daily-value-and-license-plate2")
	public ResponseEntity<List<?>> getDailyValueAndLicensePlate2() {
		return responseEntityList(service.getDailyValueAndLicensePlate2());
	}
	
	@GetMapping(value = "/get-daily-value-and-license-plate1")
	public ResponseEntity<List<?>> getDailyValueAndLicensePlate1() {
		return responseEntityList(service.getDailyValueAndLicensePlate1());
	}

	@GetMapping(value = "/get-car-by-color")
	@Override
	public ResponseEntity<List<CarGetDTO>> getCarByColorEquals(@RequestParam(required = false) String searchColor) {
		return responseDtoList(service.getCarByColorEquals(searchColor));
	}

	@GetMapping(value = "/get-cars-with-a-daily-rate-greater-than-or-equal-to-the-average")
	@Override
	public ResponseEntity<List<CarGetDTO>> getCarsWithADailyRateGreaterThanOrEqualToTheAverage() {
		return responseDtoList(service.getCarsWithADailyRateGreaterThanOrEqualToTheAverage());
	}

	@GetMapping(value = "/get-never-rented-cars")
	@Override
	public ResponseEntity<List<CarGetDTO>> getNeverRentedCarsHibernate() {
		return responseEntityListToDto(service.getNeverRentedCarsHibernate());
	}

	@GetMapping(value = "/total-rental-value-per-car")
	@Override
	public ResponseEntity<List<?>> totalRentalValuePerCarHibernate() {
		return responseEntityList(service.totalRentalValuePerCarHibernate());
	}

	@GetMapping(value = "/get-cars-of-manufacturer")
	@Override
	public ResponseEntity<List<CarGetDTO>> getCarsOfManufacturerHibernate(@RequestParam String manufacturerName) {
		return responseEntityListToDto(service.getCarsOfManufacturerHibernate(manufacturerName));
	}
	
	@Override
	public ResponseEntity<List<CarGetDTO>> filter(Car entity, String... properties) {
		return super.filter(entity, "color");
	}

}
