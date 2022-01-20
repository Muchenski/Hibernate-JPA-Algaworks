package com.locadora.jpa.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.locadora.jpa.dtos.accessory.AccessoryGetDTO;

public interface AccessoryResourceContract {

	public abstract ResponseEntity<List<AccessoryGetDTO>> getAccessoriesByCar(Long carId);
	
}
