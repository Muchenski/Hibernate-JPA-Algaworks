package com.locadora.jpa.contracts;

import java.util.Date;
import java.util.List;

import com.locadora.jpa.model.domain.Rent;

public interface RentModelContract {

	public abstract List<Rent> getByDeliveryDateAndCarModel(Date deliveryDate, Long carModelId);
	
	public abstract List<Rent> getByDeliveryDateAndCarModelHibernate(Date deliveryDate, Long carModelId);
	
	public abstract Double getTotalRevenue();
	
	public abstract Double getTotalRevenueOfMonthHibernate(int month);

}
