package com.locadora.jpa.dtos.car;

import java.time.LocalDateTime;

import com.locadora.jpa.dtos.DefaultDTO;

public class CarNewDTO extends DefaultDTO {

	private static final long serialVersionUID = 1L;

	private String licensePlate;

	private String chassis;

	private String color;

	private Double dailyValue;

	private Long carModelId;

	public CarNewDTO() {
		super();
	}

	public CarNewDTO(LocalDateTime creationDate, LocalDateTime updateDate, String licensePlate, String chassis,
			String color, Double dailyValue, Long carModelId) {
		super(creationDate, updateDate);
		this.licensePlate = licensePlate;
		this.chassis = chassis;
		this.color = color;
		this.dailyValue = dailyValue;
		this.carModelId = carModelId;
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

	public Long getCarModelId() {
		return carModelId;
	}

	public void setCarModelId(Long carModelId) {
		this.carModelId = carModelId;
	}

}
