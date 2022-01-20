package com.locadora.jpa.model.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "locadora")
public class Accessory extends DefaultBaseDomain {

	private static final long serialVersionUID = 1L;

	private String description;

	@ManyToMany(mappedBy = "accessories")
	private List<Car> cars = new ArrayList<Car>();

	public Accessory() {
		super();
	}

	public Accessory(Long id, LocalDateTime creationDate, LocalDateTime updateDate, String description) {
		super(id, creationDate, updateDate);
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
