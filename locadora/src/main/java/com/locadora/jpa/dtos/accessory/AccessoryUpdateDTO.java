package com.locadora.jpa.dtos.accessory;

import java.time.LocalDateTime;

import com.locadora.jpa.dtos.DefaultResourceableDTO;

public class AccessoryUpdateDTO extends DefaultResourceableDTO {

	private static final long serialVersionUID = 1L;

	private String description;

	public AccessoryUpdateDTO() {
		super();
	}

	public AccessoryUpdateDTO(LocalDateTime creationDate, LocalDateTime updateDate, Long id, String description) {
		super(creationDate, updateDate, id);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
