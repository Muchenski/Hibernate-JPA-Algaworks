package com.locadora.jpa.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.locadora.jpa.contracts.ManufacturerModelContract;
import com.locadora.jpa.model.dao.ManufacturerDAO;
import com.locadora.jpa.model.domain.Manufacturer;

@Service
public class ManufacturerService extends GenericService<Manufacturer, Long, ManufacturerDAO> implements ManufacturerModelContract {

	public ManufacturerService(ManufacturerDAO manufacturerDAO) {
		super(manufacturerDAO, Manufacturer.class);
	}

	@Override
	public List<Manufacturer> byNameLike(String name) {
		return dao.byNameLike(name);
	}

}
