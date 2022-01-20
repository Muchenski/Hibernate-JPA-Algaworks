package com.locadora.jpa.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.locadora.jpa.dtos.rent.RentGetDTO;

public interface RentResourceContract {

	public abstract ResponseEntity<List<RentGetDTO>> getByDeliveryDateAndCarModel(String deliveryDate, Long carModelId);

	public abstract ResponseEntity<List<RentGetDTO>> getByDeliveryDateAndCarModelHibernate(String deliveryDate, Long carModelId);
	
	public abstract ResponseEntity<Double> getTotalRevenue();
	
	public abstract ResponseEntity<Double> getTotalRevenueOfMonthHibernate(int month);
	
}
