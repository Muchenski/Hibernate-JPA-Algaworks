package com.locadora.jpa.model.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "locadora")
public class Car extends DefaultBaseDomain {

	private static final long serialVersionUID = 1L;

	private String licensePlate;

	private String chassis;

	private String color;

	private Double dailyValue;

	@ManyToOne
	@JoinColumn(name = "car_model_id")
	private CarModel carModel;

	@ManyToMany
	@JoinTable(name = "locadora.car_accessory", joinColumns = @JoinColumn(name = "car_id"), inverseJoinColumns = @JoinColumn(name = "accessory_id"))
	private List<Accessory> accessories = new ArrayList<Accessory>();

	@OneToMany(mappedBy = "car")
	private List<Rent> rents = new ArrayList<Rent>();

	public Car() {
		super();
	}

	public Car(Long id, LocalDateTime creationDate, LocalDateTime updateDate, String licensePlate, String chassis,
			String color, Double dailyValue, CarModel carModel) {
		super(id, creationDate, updateDate);
		this.licensePlate = licensePlate;
		this.chassis = chassis;
		this.color = color;
		this.dailyValue = dailyValue;
		this.carModel = carModel;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getChassis() {
		return chassis;
	}

	public void setChassis(String chassis) {
		this.chassis = chassis;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getDailyValue() {
		return dailyValue;
	}

	public void setDailyValue(Double dailyValue) {
		this.dailyValue = dailyValue;
	}

	public CarModel getCarModel() {
		return carModel;
	}

	public void setCarModel(CarModel carModel) {
		this.carModel = carModel;
	}

	public List<Accessory> getAccessories() {
		return accessories;
	}

	public void setAccessories(List<Accessory> accessories) {
		this.accessories = accessories;
	}

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

}
