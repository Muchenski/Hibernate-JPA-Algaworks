package com.locadora.jpa.model.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "locadora")
public class InsurancePolicy extends DefaultBaseDomain {

	private static final long serialVersionUID = 1L;

	private Double franchiseValue;

	private Boolean thirdPartyProtection;

	private Boolean protectionFromNaturalCauses;

	private Boolean theftProtection;

	public InsurancePolicy() {
		super();
	}

	public InsurancePolicy(Long id, LocalDateTime creationDate, LocalDateTime updateDate, Double franchiseValue, Boolean thirdPartyProtection,
			Boolean protectionFromNaturalCauses, Boolean theftProtection) {
		super(id, creationDate, updateDate);
		this.franchiseValue = franchiseValue;
		this.thirdPartyProtection = thirdPartyProtection;
		this.protectionFromNaturalCauses = protectionFromNaturalCauses;
		this.theftProtection = theftProtection;
	}

	public Double getFranchiseValue() {
		return franchiseValue;
	}

	public void setFranchiseValue(Double franchiseValue) {
		this.franchiseValue = franchiseValue;
	}

	public Boolean getThirdPartyProtection() {
		return thirdPartyProtection;
	}

	public void setThirdPartyProtection(Boolean thirdPartyProtection) {
		this.thirdPartyProtection = thirdPartyProtection;
	}

	public Boolean getProtectionFromNaturalCauses() {
		return protectionFromNaturalCauses;
	}

	public void setProtectionFromNaturalCauses(Boolean protectionFromNaturalCauses) {
		this.protectionFromNaturalCauses = protectionFromNaturalCauses;
	}

	public Boolean getTheftProtection() {
		return theftProtection;
	}

	public void setTheftProtection(Boolean theftProtection) {
		this.theftProtection = theftProtection;
	}

}
