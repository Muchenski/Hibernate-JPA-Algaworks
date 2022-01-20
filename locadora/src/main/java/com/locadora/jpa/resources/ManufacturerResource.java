package com.locadora.jpa.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.jpa.contracts.ManufacturerResourceContract;
import com.locadora.jpa.converter.ManufacturerConverter;
import com.locadora.jpa.dtos.manufacturer.ManufacturerGetDTO;
import com.locadora.jpa.dtos.manufacturer.ManufacturerNewDTO;
import com.locadora.jpa.dtos.manufacturer.ManufacturerUpdateDTO;
import com.locadora.jpa.model.domain.Manufacturer;
import com.locadora.jpa.model.service.ManufacturerService;

@RestController
@RequestMapping(value = "/manufacturers")
public class ManufacturerResource extends
		GenericResource<Manufacturer, Long, ManufacturerService, ManufacturerGetDTO, ManufacturerNewDTO, ManufacturerUpdateDTO> implements ManufacturerResourceContract {

	public ManufacturerResource(ManufacturerService service, ManufacturerConverter converter) {
		super(service, converter);
	}

	@GetMapping(value = "/byNameLike")
	@Override
	public ResponseEntity<List<ManufacturerGetDTO>> byNameLike(@RequestParam String name) {
		return responseEntityListToDto(service.byNameLike(name));
	}

}
