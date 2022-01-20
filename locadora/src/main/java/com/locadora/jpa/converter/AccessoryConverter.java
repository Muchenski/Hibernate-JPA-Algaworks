package com.locadora.jpa.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.locadora.jpa.dtos.accessory.AccessoryGetDTO;
import com.locadora.jpa.dtos.accessory.AccessoryNewDTO;
import com.locadora.jpa.dtos.accessory.AccessoryUpdateDTO;
import com.locadora.jpa.model.domain.Accessory;

@Component
public class AccessoryConverter implements Converter<Accessory, Long, AccessoryGetDTO, AccessoryNewDTO, AccessoryUpdateDTO> {

	@Override
	public AccessoryGetDTO entityToDtoOnGet(Accessory entity) {
		AccessoryGetDTO dto = new AccessoryGetDTO();
		
		dto.setId(entity.getId());
		dto.setCreationDate(entity.getCreationDate());
		dto.setUpdateDate(entity.getUpdateDate());
		dto.setDescription(entity.getDescription());
		
		return dto;
	}

	@Override
	public List<AccessoryGetDTO> entityToDtoOnGet(List<Accessory> entities) {
		return entities.stream().map(e -> entityToDtoOnGet(e)).collect(Collectors.toList());
	}

	@Override
	public Accessory dtoToEntityOnSave(AccessoryNewDTO dto) {
		Accessory entity = new Accessory();
		
		entity.setDescription(dto.getDescription());
		
		return entity;
	}

	@Override
	public Accessory dtoToEntityOnUpdate(Long id, AccessoryUpdateDTO dto) {
		Accessory entity = new Accessory();
		
		entity.setId(id);
		entity.setDescription(dto.getDescription());
		
		return entity;
	}

}
