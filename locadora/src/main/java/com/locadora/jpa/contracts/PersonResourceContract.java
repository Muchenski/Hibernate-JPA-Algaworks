package com.locadora.jpa.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface PersonResourceContract {

	public abstract ResponseEntity<List<?>> getAlertsPerPerson();
	
}
