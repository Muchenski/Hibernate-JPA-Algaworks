package com.locadora.jpa.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.locadora.jpa.contracts.RentModelContract;
import com.locadora.jpa.model.dao.RentDAO;
import com.locadora.jpa.model.domain.Rent;

@Service
public class RentService extends GenericService<Rent, Long, RentDAO> implements RentModelContract {

	public RentService(RentDAO rentDAO) {
		super(rentDAO, Rent.class);
	}

	@Override
	public List<Rent> getByDeliveryDateAndCarModel(Date deliveryDate, Long carModel) {
		return dao.getByDeliveryDateAndCarModel(deliveryDate, carModel);
	}

	@Override
	public List<Rent> getByDeliveryDateAndCarModelHibernate(Date deliveryDate, Long carModelId) {
		return dao.getByDeliveryDateAndCarModelHibernate(deliveryDate, carModelId);
	}
	
	@Override
	public Double getTotalRevenue() {
		return dao.getTotalRevenue();
	}

	@Override
	public Double getTotalRevenueOfMonthHibernate(int month) {
		return dao.getTotalRevenueOfMonthHibernate(month);
	}

}
