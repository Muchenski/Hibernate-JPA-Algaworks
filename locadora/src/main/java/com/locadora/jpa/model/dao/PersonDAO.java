package com.locadora.jpa.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.locadora.jpa.contracts.PersonModelContract;
import com.locadora.jpa.model.domain.Person;
import com.locadora.jpa.vo.AlertsPerPerson;

@Repository
public class PersonDAO extends GenericDAO<Person, Long> implements PersonModelContract {
	
	public PersonDAO() {
		super(Person.class);
	}

	@Override
	public List<AlertsPerPerson> getAlertsPerPerson() {
		return entityManager.createNamedQuery("Person.alertsPerPerson", AlertsPerPerson.class).getResultList();
	}

}
