package com.locadora.jpa.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locadora.jpa.contracts.PersonModelContract;
import com.locadora.jpa.model.dao.PersonDAO;
import com.locadora.jpa.model.domain.Person;
import com.locadora.jpa.vo.AlertsPerPerson;

@Service
public class PersonService extends GenericService<Person, Long, PersonDAO> implements PersonModelContract {

	public PersonService(PersonDAO personDAO) {
		super(personDAO, Person.class);
	}

	@Override
	public List<AlertsPerPerson> getAlertsPerPerson() {
		return dao.getAlertsPerPerson();
	}

}
