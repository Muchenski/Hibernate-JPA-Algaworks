package com.locadora.jpa.model.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.locadora.jpa.model.domain.enums.Gender;

@Entity
@Table(schema = "locadora")
@DiscriminatorValue(value = "driver")
public class Driver extends Person {

	private static final long serialVersionUID = 1L;

	private String driversLicense;

	@OneToMany(mappedBy = "driver")
	private List<Rent> rents = new ArrayList<Rent>();

	public Driver() {
		super();
	}

	public Driver(Long id, LocalDateTime creationDate, LocalDateTime updateDate, String name, Date birthDate,
			String cpf, String driversLicense, Gender gender) {
		super(id, creationDate, updateDate, name, birthDate, cpf, gender);
		this.driversLicense = driversLicense;
	}

	public String getDriversLicense() {
		return driversLicense;
	}

	public void setDriversLicense(String driversLicense) {
		this.driversLicense = driversLicense;
	}

	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

}
