package com.locadora.jpa.dtos.accessory;

import java.time.LocalDateTime;

import com.locadora.jpa.dtos.DefaultDTO;

public class AccessoryNewDTO extends DefaultDTO {

	private static final long serialVersionUID = 1L;

	private String description;

	public AccessoryNewDTO() {
		super();
	}

	public AccessoryNewDTO(LocalDateTime creationDate, LocalDateTime updateDate, String description) {
		super(creationDate, updateDate);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
