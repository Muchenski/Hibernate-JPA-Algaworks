package com.locadora.jpa.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.jpa.converter.CarModelConverter;
import com.locadora.jpa.dtos.car_model.CarModelGetDTO;
import com.locadora.jpa.dtos.car_model.CarModelNewDTO;
import com.locadora.jpa.dtos.car_model.CarModelUpdateDTO;
import com.locadora.jpa.model.domain.CarModel;
import com.locadora.jpa.model.service.CarModelService;

@RestController
@RequestMapping(value = "/car-models")
public class CarModelResource extends GenericResource<CarModel, Long, CarModelService, CarModelGetDTO, CarModelNewDTO, CarModelUpdateDTO> {

	public CarModelResource(CarModelService service, CarModelConverter converter) {
		super(service, converter);
	}

}
