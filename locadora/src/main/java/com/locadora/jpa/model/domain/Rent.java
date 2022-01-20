package com.locadora.jpa.model.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "locadora")
public class Rent extends DefaultBaseDomain {

	private static final long serialVersionUID = 1L;

	private LocalDateTime requestDate;

	private LocalDateTime deliveryDate;

	private LocalDateTime returnDate;

	private Double amount;

	// orphanRemoval = true -> Caso a referência de insurancePolicy seja removida da classe Rent, ela será apagada.
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "insurance_policy_id")
	private InsurancePolicy insurancePolicy;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

	@ManyToOne
	@JoinColumn(name = "driver_id")
	private Driver driver;

	public Rent() {
		super();
	}

	public Rent(Long id, LocalDateTime creationDate, LocalDateTime updateDate, LocalDateTime requestDate, LocalDateTime deliveryDate,
			LocalDateTime returnDate, Double amount, Car car, InsurancePolicy insurancePolicy, Driver driver) {
		super(id, creationDate, updateDate);
		this.requestDate = requestDate;
		this.deliveryDate = deliveryDate;
		this.returnDate = returnDate;
		this.amount = amount;
		this.car = car;
		this.insurancePolicy = insurancePolicy;
		this.driver = driver;
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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public InsurancePolicy getInsurancePolicy() {
		return insurancePolicy;
	}

	public void setInsurancePolicy(InsurancePolicy insurancePolicy) {
		this.insurancePolicy = insurancePolicy;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}
