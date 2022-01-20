package com.locadora.jpa.model.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;

	private String number;

	private String areaCode;

	public Phone() {
		super();
	}

	public Phone(String number, String areaCode) {
		super();
		this.number = number;
		this.areaCode = areaCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

}
