package com.locadora.jpa.dtos.car_model;

import java.time.LocalDateTime;

import com.locadora.jpa.dtos.DefaultResourceableDTO;
import com.locadora.jpa.model.domain.enums.Category;

public class CarModelUpdateDTO extends DefaultResourceableDTO {

	private static final long serialVersionUID = 1L;

	private String description;

	private Long manufacturerId;

	private String manufacturerName;

	private Category category;

	public CarModelUpdateDTO() {
		super();
	}

	public CarModelUpdateDTO(LocalDateTime creationDate, LocalDateTime updateDate, Long id, String description,
			Long manufacturerId, String manufacturerName, Category category) {
		super(creationDate, updateDate, id);
		this.description = description;
		this.manufacturerId = manufacturerId;
		this.manufacturerName = manufacturerName;
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

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
