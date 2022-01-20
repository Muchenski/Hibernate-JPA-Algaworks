package com.locadora.jpa.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.jpa.contracts.RentResourceContract;
import com.locadora.jpa.converter.RentConverter;
import com.locadora.jpa.dtos.rent.RentGetDTO;
import com.locadora.jpa.dtos.rent.RentNewDTO;
import com.locadora.jpa.dtos.rent.RentUpdateDTO;
import com.locadora.jpa.model.domain.Rent;
import com.locadora.jpa.model.service.RentService;

@RestController
@RequestMapping(value = "/rents")
public class RentResource extends GenericResource<Rent, Long, RentService, RentGetDTO, RentNewDTO, RentUpdateDTO>
		implements RentResourceContract {

	public RentResource(RentService service, RentConverter converter) {
		super(service, converter);
	}

	@GetMapping(value = "/get-by-delivery-date-and-car-model")
	@Override
	public ResponseEntity<List<RentGetDTO>> getByDeliveryDateAndCarModel(
			@RequestParam(required = false, name = "deliveryDate") String deliveryDateReq,
			@RequestParam(required = false) Long carModelId
			) 
	{
		Date deliveryDate = null;
		try {
			deliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDateReq);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format!");
		}
		
		List<Rent> results = service.getByDeliveryDateAndCarModel(deliveryDate, carModelId);
		return responseEntityListToDto(results);
	}
	
	@GetMapping(value = "/get-by-delivery-date-and-car-model-hibernate")
	@Override
	public ResponseEntity<List<RentGetDTO>> getByDeliveryDateAndCarModelHibernate(
			@RequestParam(required = false, name = "deliveryDate") String deliveryDateReq,
			@RequestParam(required = false) Long carModelId
			) 
	{
		Date deliveryDate = null;
		try {
			deliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDateReq);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Invalid date format!");
		}
		return responseEntityListToDto(service.getByDeliveryDateAndCarModelHibernate(deliveryDate, carModelId));
	}

	@GetMapping(value = "/total-revenue")
	@Override
	public ResponseEntity<Double> getTotalRevenue() {
		return ResponseEntity.ok(service.getTotalRevenue());
	}

	@GetMapping(value = "/total-revenue-of-month")
	@Override
	public ResponseEntity<Double> getTotalRevenueOfMonthHibernate(@RequestParam int month) {
		return ResponseEntity.ok(service.getTotalRevenueOfMonthHibernate(month));
	}

}
