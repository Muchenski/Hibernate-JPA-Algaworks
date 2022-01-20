package com.locadora.jpa.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.locadora.jpa.contracts.Crud;
import com.locadora.jpa.contracts.CrudResource;
import com.locadora.jpa.contracts.Resourceable;
import com.locadora.jpa.converter.Converter;
import com.locadora.jpa.dtos.DefaultDTO;
import com.locadora.jpa.dtos.DefaultResourceableDTO;

@RestController
public abstract class GenericResource<T extends Resourceable<ID>, ID, S extends Crud<T, ID>, DG extends DefaultResourceableDTO, DS extends DefaultDTO, DU extends DefaultResourceableDTO>
		implements CrudResource<T, ID, DG, DS, DU> {

	protected S service;

	protected Converter<T, ID, DG, DS, DU> converter;

	public <C extends Converter<T, ID, DG, DS, DU>> GenericResource(S service, C converter) {
		super();
		this.service = service;
		this.converter = converter;
	}

	@GetMapping(value = "/{id}")
	@Override
	public ResponseEntity<DG> findById(@PathVariable ID id) {
		return ResponseEntity.ok(converter.entityToDtoOnGet(service.findById(id)));
	}

	@GetMapping
	@Override
	public ResponseEntity<List<DG>> findAll() {
		List<T> results = service.findAll();
		return results.isEmpty() ? ResponseEntity.noContent().build()
				: ResponseEntity.ok(converter.entityToDtoOnGet(results));
	}

	@PostMapping
	@Override
	public ResponseEntity<DG> save(@RequestBody DS dto) {
		return ResponseEntity.ok(converter.entityToDtoOnGet(service.save(converter.dtoToEntityOnSave(dto))));
	}

	@PutMapping(value = "/{id}")
	@Override
	public ResponseEntity<DG> update(@PathVariable ID id, @RequestBody DU dto) {
		return ResponseEntity.ok(converter.entityToDtoOnGet(service.update(id, converter.dtoToEntityOnUpdate(id, dto))));
	}

	@DeleteMapping(value = "/{id}")
	@Override
	public ResponseEntity<Void> deleteById(@PathVariable ID id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/find")
	@Override
	public ResponseEntity<List<DG>> find(
			@RequestParam(defaultValue = "0", name = "firstRow") int firstRow,
			@RequestParam(defaultValue = "10", name = "pageSize") int pageSize
			) 
	{
		List<T> results = service.find(firstRow, pageSize);
		return responseEntityListToDto(results);
	}
	
	@GetMapping(value = "/filter")
	@Override
	public ResponseEntity<List<DG>> filter(T entity, String... properties) {
		List<T> results = service.filter(entity, properties);
		return responseEntityListToDto(results);
	}
	
	@Override
	public ResponseEntity<List<DG>> responseEntityListToDto(List<T> list) {
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(converter.entityToDtoOnGet(list));
	}
	
	@Override
	public ResponseEntity<List<?>> responseEntityList(List<?> list) {
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}
	
	@Override
	public <D extends DefaultDTO> ResponseEntity<List<D>> responseDtoList(List<D> list) {
		return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
	}

}
