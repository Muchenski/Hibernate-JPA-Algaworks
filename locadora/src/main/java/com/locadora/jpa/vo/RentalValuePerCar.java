package com.locadora.jpa.vo;

import java.io.Serializable;

public class RentalValuePerCar implements Serializable {

	private static final long serialVersionUID = 1L;

	private String licensePlate;

	private Long rents;

	private Double totalValue;

	public RentalValuePerCar() {
		super();
	}

	public RentalValuePerCar(String licensePlate, Long rents, Double totalValue) {
		super();
		this.licensePlate = licensePlate;
		this.rents = rents;
		this.totalValue = totalValue;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public Long getRents() {
		return rents;
	}

	public void setRents(Long rents) {
		this.rents = rents;
	}

}
