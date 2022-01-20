package com.locadora.jpa.model.service;

import org.springframework.stereotype.Service;

import com.locadora.jpa.model.dao.CarModelDAO;
import com.locadora.jpa.model.domain.CarModel;

@Service
public class CarModelService extends GenericService<CarModel, Long, CarModelDAO> {

	public CarModelService(CarModelDAO carModelDAO) {
		super(carModelDAO, CarModel.class);
	}

}
