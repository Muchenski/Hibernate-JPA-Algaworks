package com.locadora.jpa.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.locadora.jpa.dtos.person.PersonGetDTO;
import com.locadora.jpa.dtos.person.PersonNewDTO;
import com.locadora.jpa.dtos.person.PersonUpdateDTO;
import com.locadora.jpa.model.domain.Person;

@Component
public class PersonConverter implements Converter<Person, Long, PersonGetDTO, PersonNewDTO, PersonUpdateDTO> {

	@Override
	public PersonGetDTO entityToDtoOnGet(Person entity) {
		PersonGetDTO dto = new PersonGetDTO();
		
		dto.setId(entity.getId());
		dto.setCreationDate(entity.getCreationDate());
		dto.setUpdateDate(entity.getUpdateDate());
		dto.setName(entity.getName());
		dto.setBirthDate(entity.getBirthDate());
		dto.setCpf(entity.getCpf());
		dto.setGender(entity.getGender());
		
		return dto;
	}

	@Override
	public List<PersonGetDTO> entityToDtoOnGet(List<Person> entities) {
		return entities.stream().map(e -> entityToDtoOnGet(e)).collect(Collectors.toList());
	}

	@Override
	public Person dtoToEntityOnSave(PersonNewDTO dto) {
		Person entity = new Person();
		
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setCpf(dto.getCpf());
		entity.setGender(dto.getGender());
		
		return entity;
	}

	@Override
	public Person dtoToEntityOnUpdate(Long id, PersonUpdateDTO dto) {
		Person entity = new Person();
		
		entity.setId(id);
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setCpf(dto.getCpf());
		entity.setGender(dto.getGender());
		
		return entity;
	}

}
