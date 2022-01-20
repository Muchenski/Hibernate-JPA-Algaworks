package com.locadora.jpa.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.locadora.jpa.dtos.car_model.CarModelGetDTO;
import com.locadora.jpa.dtos.car_model.CarModelNewDTO;
import com.locadora.jpa.dtos.car_model.CarModelUpdateDTO;
import com.locadora.jpa.model.domain.CarModel;
import com.locadora.jpa.model.domain.Manufacturer;

@Component
public class CarModelConverter implements Converter<CarModel, Long, CarModelGetDTO, CarModelNewDTO, CarModelUpdateDTO> {

	@Override
	public CarModelGetDTO entityToDtoOnGet(CarModel entity) {
		CarModelGetDTO dto = new CarModelGetDTO();

		Manufacturer manufacturer = entity.getManufacturer();

		dto.setId(entity.getId());
		dto.setCreationDate(entity.getCreationDate());
		dto.setUpdateDate(entity.getUpdateDate());
		dto.setDescription(entity.getDescription());

		if (manufacturer != null) {
			dto.setManufacturerId(manufacturer.getId());
			dto.setManufacturerName(manufacturer.getName());
		}

		dto.setCategory(entity.getCategory());

		return dto;
	}

	@Override
	public List<CarModelGetDTO> entityToDtoOnGet(List<CarModel> entities) {
		return entities.stream().map(e -> entityToDtoOnGet(e)).collect(Collectors.toList());
	}

	@Override
	public CarModel dtoToEntityOnSave(CarModelNewDTO dto) {
		CarModel entity = new CarModel();

		Long manufacturerId = dto.getManufacturerId();

		entity.setDescription(dto.getDescription());

		if (manufacturerId != null) {
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setId(manufacturerId);
			entity.setManufacturer(manufacturer);
		}

		entity.setCategory(dto.getCategory());

		return entity;
	}

	@Override
	public CarModel dtoToEntityOnUpdate(Long id, CarModelUpdateDTO dto) {
		CarModel entity = new CarModel();

		Long manufacturerId = dto.getManufacturerId();

		entity.setId(id);
		entity.setDescription(dto.getDescription());

		if (manufacturerId != null) {
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setId(manufacturerId);
			entity.setManufacturer(manufacturer);
		}

		entity.setCategory(dto.getCategory());

		return entity;
	}

}
