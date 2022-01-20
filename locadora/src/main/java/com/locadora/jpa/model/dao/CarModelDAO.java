package com.locadora.jpa.model.dao;

import org.springframework.stereotype.Repository;

import com.locadora.jpa.model.domain.CarModel;

@Repository
public class CarModelDAO extends GenericDAO<CarModel, Long> {
	
	public CarModelDAO() {
		super(CarModel.class);
	}

}
