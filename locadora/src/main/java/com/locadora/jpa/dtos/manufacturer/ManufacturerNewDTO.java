package com.locadora.jpa.dtos.manufacturer;

import java.time.LocalDateTime;

import com.locadora.jpa.dtos.DefaultDTO;

public class ManufacturerNewDTO extends DefaultDTO {

	private static final long serialVersionUID = 1L;

	private String name;

	public ManufacturerNewDTO() {
		super();
	}

	public ManufacturerNewDTO(LocalDateTime creationDate, LocalDateTime updateDate, String name) {
		super(creationDate, updateDate);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
