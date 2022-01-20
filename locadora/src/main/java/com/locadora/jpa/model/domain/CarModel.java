package com.locadora.jpa.model.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.locadora.jpa.model.domain.enums.Category;

@Entity
@Table(schema = "locadora")
public class CarModel extends DefaultBaseDomain {

	private static final long serialVersionUID = 1L;

	private String description;

	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	@OneToMany(mappedBy = "carModel")
	private List<Car> cars = new ArrayList<Car>();

	@Enumerated(EnumType.STRING)
	private Category category;

	public CarModel() {
		super();
	}

	public CarModel(Long id, LocalDateTime creationDate, LocalDateTime updateDate, String description,
			Manufacturer manufacturer, Category category) {
		super(id, creationDate, updateDate);
		this.description = description;
		this.manufacturer = manufacturer;
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
