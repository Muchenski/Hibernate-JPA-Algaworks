package com.locadora.jpa.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.locadora.jpa.dtos.manufacturer.ManufacturerGetDTO;

public interface ManufacturerResourceContract {
	
	public abstract ResponseEntity<List<ManufacturerGetDTO>> byNameLike(String name);
	
}
