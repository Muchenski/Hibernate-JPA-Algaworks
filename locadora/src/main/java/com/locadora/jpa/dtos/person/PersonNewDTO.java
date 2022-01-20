package com.locadora.jpa.dtos.person;

import java.time.LocalDateTime;
import java.util.Date;

import com.locadora.jpa.dtos.DefaultDTO;
import com.locadora.jpa.model.domain.enums.Gender;

public class PersonNewDTO extends DefaultDTO {

	private static final long serialVersionUID = 1L;

	protected String name;

	protected Date birthDate;

	protected String cpf;

	protected Gender gender;

	public PersonNewDTO() {
		super();
	}

	public PersonNewDTO(LocalDateTime creationDate, LocalDateTime updateDate, String name, Date birthDate, String cpf,
			Gender gender) {
		super(creationDate, updateDate);
		this.name = name;
		this.birthDate = birthDate;
		this.cpf = cpf;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

}
