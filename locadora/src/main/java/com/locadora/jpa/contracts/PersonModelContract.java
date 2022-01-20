package com.locadora.jpa.contracts;

import java.util.List;

import com.locadora.jpa.vo.AlertsPerPerson;

public interface PersonModelContract {

	public abstract List<AlertsPerPerson> getAlertsPerPerson();
	
}
