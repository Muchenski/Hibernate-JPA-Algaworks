package com.locadora.jpa.converter;

import java.util.List;

import com.locadora.jpa.contracts.Resourceable;
import com.locadora.jpa.dtos.DefaultDTO;
import com.locadora.jpa.dtos.DefaultResourceableDTO;

public interface Converter<T extends Resourceable<ID>, ID, DG extends DefaultResourceableDTO, DS extends DefaultDTO, DU extends DefaultResourceableDTO> {

	public abstract DG entityToDtoOnGet(T entity);

	public abstract List<DG> entityToDtoOnGet(List<T> entities);

	public abstract T dtoToEntityOnSave(DS dto);

	public abstract T dtoToEntityOnUpdate(ID id, DU dto);

}
