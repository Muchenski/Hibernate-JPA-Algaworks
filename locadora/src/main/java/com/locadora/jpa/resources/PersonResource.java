package com.locadora.jpa.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.jpa.contracts.PersonResourceContract;
import com.locadora.jpa.converter.PersonConverter;
import com.locadora.jpa.dtos.person.PersonGetDTO;
import com.locadora.jpa.dtos.person.PersonNewDTO;
import com.locadora.jpa.dtos.person.PersonUpdateDTO;
import com.locadora.jpa.model.domain.Person;
import com.locadora.jpa.model.service.PersonService;

@RestController
@RequestMapping(value = "/persons")
public class PersonResource
		extends GenericResource<Person, Long, PersonService, PersonGetDTO, PersonNewDTO, PersonUpdateDTO> implements PersonResourceContract {

	public PersonResource(PersonService service, PersonConverter converter) {
		super(service, converter);
	}

	@GetMapping(value = "/get-alerts-per-person")
	@Override
	public ResponseEntity<List<?>> getAlertsPerPerson() {
		return responseEntityList(service.getAlertsPerPerson());
	}

}
