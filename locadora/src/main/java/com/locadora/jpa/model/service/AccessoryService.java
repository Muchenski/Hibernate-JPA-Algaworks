package com.locadora.jpa.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locadora.jpa.contracts.AccessoryModelContract;
import com.locadora.jpa.model.dao.AccessoryDAO;
import com.locadora.jpa.model.domain.Accessory;

@Service
public class AccessoryService extends GenericService<Accessory, Long, AccessoryDAO>
		implements AccessoryModelContract {

	public AccessoryService(AccessoryDAO accessoryDAO) {
		super(accessoryDAO, Accessory.class);
	}

	@Override
	public List<Accessory> getAccessoriesByCar(Long carId) {
		return dao.getAccessoriesByCar(carId);
	}

}
