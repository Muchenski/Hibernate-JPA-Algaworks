package com.locadora.jpa.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.jpa.contracts.AccessoryResourceContract;
import com.locadora.jpa.converter.AccessoryConverter;
import com.locadora.jpa.dtos.accessory.AccessoryGetDTO;
import com.locadora.jpa.dtos.accessory.AccessoryNewDTO;
import com.locadora.jpa.dtos.accessory.AccessoryUpdateDTO;
import com.locadora.jpa.model.domain.Accessory;
import com.locadora.jpa.model.service.AccessoryService;

@RestController
@RequestMapping(value = "/accessories")
public class AccessoryResource extends GenericResource<Accessory, Long, AccessoryService, AccessoryGetDTO, AccessoryNewDTO, AccessoryUpdateDTO>
		implements AccessoryResourceContract {

	public AccessoryResource(AccessoryService service, AccessoryConverter converter) {
		super(service, converter);
	}

	@GetMapping(value = "/acessories-by-car/{carId}")
	@Override
	public ResponseEntity<List<AccessoryGetDTO>> getAccessoriesByCar(@PathVariable Long carId) {
		List<Accessory> results = service.getAccessoriesByCar(carId);
		return responseEntityListToDto(results);
	}

}
