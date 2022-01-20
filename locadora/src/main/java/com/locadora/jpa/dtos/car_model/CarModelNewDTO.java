package com.locadora.jpa.dtos.car_model;

import java.time.LocalDateTime;

import com.locadora.jpa.dtos.DefaultDTO;
import com.locadora.jpa.model.domain.enums.Category;

public class CarModelNewDTO extends DefaultDTO {

	private static final long serialVersionUID = 1L;

	private String description;

	private Long manufacturerId;

	private Category category;

	public CarModelNewDTO() {
		super();
	}

	public CarModelNewDTO(LocalDateTime creationDate, LocalDateTime updateDate, String description, Long manufacturerId,
			Category category) {
		super(creationDate, updateDate);
		this.description = description;
		this.manufacturerId = manufacturerId;
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
