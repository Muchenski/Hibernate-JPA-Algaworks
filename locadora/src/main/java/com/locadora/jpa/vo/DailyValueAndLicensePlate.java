package com.locadora.jpa.vo;

import java.io.Serializable;

public class DailyValueAndLicensePlate implements Serializable {

	private static final long serialVersionUID = 1L;

	private String licensePlate;

	private Double dailyValue;

	public DailyValueAndLicensePlate() {
		super();
	}

	public DailyValueAndLicensePlate(Double dailyValue, String licensePlate) {
		super();
		this.dailyValue = dailyValue;
		this.licensePlate = licensePlate;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Double getDailyValue() {
		return dailyValue;
	}

	public void setDailyValue(Double dailyValue) {
		this.dailyValue = dailyValue;
	}

}
