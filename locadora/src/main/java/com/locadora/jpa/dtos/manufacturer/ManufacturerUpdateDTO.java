package com.locadora.jpa.dtos.manufacturer;

import java.time.LocalDateTime;

import com.locadora.jpa.dtos.DefaultResourceableDTO;

public class ManufacturerUpdateDTO extends DefaultResourceableDTO {

	private static final long serialVersionUID = 1L;

	private String name;

	public ManufacturerUpdateDTO() {
		super();
	}

	public ManufacturerUpdateDTO(LocalDateTime creationDate, LocalDateTime updateDate, Long id, String name) {
		super(creationDate, updateDate, id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
