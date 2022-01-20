package com.locadora.jpa.contracts;

import java.util.List;

import com.locadora.jpa.model.domain.Accessory;

public interface AccessoryModelContract {

	public abstract List<Accessory> getAccessoriesByCar(Long carId);

}
