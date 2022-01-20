package com.locadora.jpa.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.locadora.jpa.dtos.DefaultDTO;

public interface CrudResource<T extends Resourceable<ID>, ID, DG extends DefaultDTO, DS extends DefaultDTO, DU extends DefaultDTO> {

	public abstract ResponseEntity<DG> findById(ID id);

	public abstract ResponseEntity<List<DG>> findAll();

	public abstract ResponseEntity<DG> save(DS dto);

	public abstract ResponseEntity<DG> update(ID id, DU dto);

	public abstract ResponseEntity<Void> deleteById(ID id);

	public abstract ResponseEntity<List<DG>> find(int firstRow, int pageSize);

	public abstract ResponseEntity<List<DG>> responseEntityListToDto(List<T> list);
	
	public abstract ResponseEntity<List<DG>> filter(T entity, String... properties);
	
	public abstract ResponseEntity<List<?>> responseEntityList(List<?> list);
	
	public abstract <D extends DefaultDTO> ResponseEntity<List<D>> responseDtoList(List<D> list);
	
}
