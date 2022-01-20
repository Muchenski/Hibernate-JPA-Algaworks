package com.locadora.jpa.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.locadora.jpa.dtos.car.CarGetDTO;
import com.locadora.jpa.dtos.car.CarNewDTO;
import com.locadora.jpa.dtos.car.CarUpdateDTO;
import com.locadora.jpa.model.domain.Car;
import com.locadora.jpa.model.domain.CarModel;

@Component
public class CarConverter implements Converter<Car, Long, CarGetDTO, CarNewDTO, CarUpdateDTO> {

	@Override
	public CarGetDTO entityToDtoOnGet(Car entity) {
		CarGetDTO dto = new CarGetDTO();

		CarModel carModel = entity.getCarModel();

		dto.setId(entity.getId());
		dto.setCreationDate(entity.getCreationDate());
		dto.setUpdateDate(entity.getUpdateDate());
		dto.setLicensePlate(entity.getLicensePlate());
		dto.setChassis(entity.getChassis());
		dto.setColor(entity.getColor());
		dto.setDailyValue(entity.getDailyValue());

		if (carModel != null) {
			dto.setCarModelId(carModel.getId());
			dto.setCarModelDescription(carModel.getDescription());
		}

		return dto;
	}

	@Override
	public List<CarGetDTO> entityToDtoOnGet(List<Car> entities) {
		return entities.stream().map(e -> entityToDtoOnGet(e)).collect(Collectors.toList());
	}

	@Override
	public Car dtoToEntityOnSave(CarNewDTO dto) {
		Car entity = new Car();

		Long carModelId = dto.getCarModelId();

		entity.setLicensePlate(dto.getLicensePlate());
		entity.setChassis(dto.getChassis());
		entity.setColor(dto.getColor());
		entity.setDailyValue(dto.getDailyValue());

		if (carModelId != null) {
			CarModel carModel = new CarModel();
			carModel.setId(carModelId);
			entity.setCarModel(carModel);
		}

		return entity;
	}

	@Override
	public Car dtoToEntityOnUpdate(Long id, CarUpdateDTO dto) {
		Car entity = new Car();

		Long carModelId = dto.getCarModelId();

		entity.setId(id);
		entity.setLicensePlate(dto.getLicensePlate());
		entity.setChassis(dto.getChassis());
		entity.setColor(dto.getColor());
		entity.setDailyValue(dto.getDailyValue());

		if (carModelId != null) {
			CarModel carModel = new CarModel();
			carModel.setId(carModelId);
			entity.setCarModel(carModel);
		}

		return entity;
	}

}
