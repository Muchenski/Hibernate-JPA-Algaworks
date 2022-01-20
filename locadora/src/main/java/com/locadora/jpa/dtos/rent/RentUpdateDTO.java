package com.locadora.jpa.dtos.rent;

import java.time.LocalDateTime;

import com.locadora.jpa.dtos.DefaultResourceableDTO;
import com.locadora.jpa.model.domain.InsurancePolicy;

public class RentUpdateDTO extends DefaultResourceableDTO {

	private static final long serialVersionUID = 1L;

	private LocalDateTime requestDate;

	private LocalDateTime deliveryDate;

	private LocalDateTime returnDate;

	private Double amount;

	private InsurancePolicy insurancePolicy;

	private Long carId;

	private String carLicensePlate;

	private Long driverId;

	private String driversLicense;

	public RentUpdateDTO() {
		super();
	}

	public RentUpdateDTO(LocalDateTime creationDate, LocalDateTime updateDate, Long id, LocalDateTime requestDate,
			LocalDateTime deliveryDate, LocalDateTime returnDate, Double amount, InsurancePolicy insurancePolicy,
			Long carId, String carLicensePlate, Long driverId, String driversLicense) {
		super(creationDate, updateDate, id);
		this.requestDate = requestDate;
		this.deliveryDate = deliveryDate;
		this.returnDate = returnDate;
		this.amount = amount;
		this.insurancePolicy = insurancePolicy;
		this.carId = carId;
		this.carLicensePlate = carLicensePlate;
		this.driverId = driverId;
		this.driversLicense = driversLicense;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public InsurancePolicy getInsurancePolicy() {
		return insurancePolicy;
	}

	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public String getCarLicensePlate() {
		return carLicensePlate;
	}

	public void setCarLicensePlate(String carLicensePlate) {
		this.carLicensePlate = carLicensePlate;
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getDriversLicense() {
		return driversLicense;
	}

	public void setDriversLicense(String driversLicense) {
		this.driversLicense = driversLicense;
	}

}
