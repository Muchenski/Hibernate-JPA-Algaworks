package com.locadora.jpa.contracts;

import java.util.List;

import com.locadora.jpa.model.domain.Manufacturer;

public interface ManufacturerModelContract {
	
	public abstract List<Manufacturer> byNameLike(String name);
	
}
