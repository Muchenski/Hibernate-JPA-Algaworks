package com.locadora.jpa.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.locadora.jpa.contracts.Datable;

public class DefaultDTO implements Datable, Serializable {

	private static final long serialVersionUID = 1L;

	protected LocalDateTime creationDate;

	protected LocalDateTime updateDate;

	public DefaultDTO() {
		super();
	}

	public DefaultDTO(LocalDateTime creationDate, LocalDateTime updateDate) {
		super();
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

	@Override
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	@Override
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	@Override
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

}
