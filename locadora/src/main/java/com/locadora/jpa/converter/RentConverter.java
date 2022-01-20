package com.locadora.jpa.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.locadora.jpa.dtos.rent.RentGetDTO;
import com.locadora.jpa.dtos.rent.RentNewDTO;
import com.locadora.jpa.dtos.rent.RentUpdateDTO;
import com.locadora.jpa.model.domain.Car;
import com.locadora.jpa.model.domain.Driver;
import com.locadora.jpa.model.domain.Rent;

@Component
public class RentConverter implements Converter<Rent, Long, RentGetDTO, RentNewDTO, RentUpdateDTO> {

	@Override
	public RentGetDTO entityToDtoOnGet(Rent entity) {
		RentGetDTO dto = new RentGetDTO();

		Car car = entity.getCar();

		Driver driver = entity.getDriver();

		dto.setId(entity.getId());
		dto.setCreationDate(entity.getCreationDate());
		dto.setUpdateDate(entity.getUpdateDate());
		dto.setRequestDate(entity.getRequestDate());
		dto.setDeliveryDate(entity.getDeliveryDate());
		dto.setReturnDate(entity.getReturnDate());
		dto.setAmount(entity.getAmount());
		dto.setInsurancePolicy(entity.getInsurancePolicy());

		if (car != null) {
			dto.setCarId(car.getId());
			dto.setCarLicensePlate(car.getLicensePlate());
		}

		if (driver != null) {
			dto.setDriverId(driver.getId());
			dto.setDriversLicense(driver.getDriversLicense());
		}

		return dto;
	}

	@Override
	public List<RentGetDTO> entityToDtoOnGet(List<Rent> entities) {
		return entities.stream().map(e -> entityToDtoOnGet(e)).collect(Collectors.toList());
	}

	@Override
	public Rent dtoToEntityOnSave(RentNewDTO dto) {
		Rent entity = new Rent();

		Long carId = dto.getCarId();

		Long driverId = dto.getDriverId();

		entity.setRequestDate(dto.getRequestDate());
		entity.setDeliveryDate(dto.getDeliveryDate());
		entity.setReturnDate(dto.getReturnDate());
		entity.setAmount(dto.getAmount());
		entity.setInsurancePolicy(dto.getInsurancePolicy());

		if (carId != null) {
			Car car = new Car();
			car.setId(carId);
			entity.setCar(car);
		}

		if (driverId != null) {
			Driver driver = new Driver();
			driver.setId(driverId);
			entity.setDriver(driver);
		}

		return entity;
	}

	@Override
	public Rent dtoToEntityOnUpdate(Long id, RentUpdateDTO dto) {
		Rent entity = new Rent();

		Long carId = dto.getCarId();

		Long driverId = dto.getDriverId();

		entity.setId(id);
		entity.setRequestDate(dto.getRequestDate());
		entity.setDeliveryDate(dto.getDeliveryDate());
		entity.setReturnDate(dto.getReturnDate());
		entity.setAmount(dto.getAmount());
		entity.setInsurancePolicy(dto.getInsurancePolicy());

		if (carId != null) {
			Car car = new Car();
			car.setId(carId);
			entity.setCar(car);
		}

		if (driverId != null) {
			Driver driver = new Driver();
			driver.setId(driverId);
			entity.setDriver(driver);
		}

		return entity;
	}

}
