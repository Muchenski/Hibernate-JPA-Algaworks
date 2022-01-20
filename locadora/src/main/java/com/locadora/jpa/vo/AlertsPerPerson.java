package com.locadora.jpa.vo;

import java.io.Serializable;

public class AlertsPerPerson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private Long alerts;

	public AlertsPerPerson() {
		super();
	}

	public AlertsPerPerson(String name, Long alerts) {
		super();
		this.name = name;
		this.alerts = alerts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAlerts() {
		return alerts;
	}

	public void setAlerts(Long alerts) {
		this.alerts = alerts;
	}

}
