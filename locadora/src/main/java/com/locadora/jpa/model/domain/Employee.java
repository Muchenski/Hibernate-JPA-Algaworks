package com.locadora.jpa.model.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.locadora.jpa.model.domain.enums.Gender;

@Entity
@Table(schema = "locadora")
@DiscriminatorValue(value = "employee")
public class Employee extends Person {

	private static final long serialVersionUID = 1L;

	private String registration;

	public Employee() {
		super();
	}

	public Employee(Long id, LocalDateTime creationDate, LocalDateTime updateDate, String name, Date birthDate,
			String cpf, String registration, Gender gender) {
		super(id, creationDate, updateDate, name, birthDate, cpf, gender);
		this.registration = registration;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

}
