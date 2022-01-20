package com.locadora.jpa.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.locadora.jpa.dtos.manufacturer.ManufacturerGetDTO;
import com.locadora.jpa.dtos.manufacturer.ManufacturerNewDTO;
import com.locadora.jpa.dtos.manufacturer.ManufacturerUpdateDTO;
import com.locadora.jpa.model.domain.Manufacturer;

@Component
public class ManufacturerConverter implements Converter<Manufacturer, Long, ManufacturerGetDTO, ManufacturerNewDTO, ManufacturerUpdateDTO> {

	@Override
	public ManufacturerGetDTO entityToDtoOnGet(Manufacturer entity) {
		ManufacturerGetDTO dto = new ManufacturerGetDTO();
		
		dto.setId(entity.getId());
		dto.setCreationDate(entity.getCreationDate());
		dto.setUpdateDate(entity.getUpdateDate());
		dto.setName(entity.getName());
		
		return dto;
	}

	@Override
	public List<ManufacturerGetDTO> entityToDtoOnGet(List<Manufacturer> entities) {
		return entities.stream().map(e -> entityToDtoOnGet(e)).collect(Collectors.toList());
	}

	@Override
	public Manufacturer dtoToEntityOnSave(ManufacturerNewDTO dto) {
		Manufacturer entity = new Manufacturer();
		
		entity.setName(dto.getName());
		
		return entity;
	}

	@Override
	public Manufacturer dtoToEntityOnUpdate(Long id, ManufacturerUpdateDTO dto) {
		Manufacturer entity = new Manufacturer();
		
		entity.setId(id);
		entity.setName(dto.getName());
		
		return entity;
	}

}
