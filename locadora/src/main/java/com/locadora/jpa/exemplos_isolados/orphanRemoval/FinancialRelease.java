package com.locadora.jpa.exemplos_isolados.orphanRemoval;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.locadora.jpa.model.domain.DefaultBaseDomain;

@Entity
@Table(schema = "locadora")
public class FinancialRelease extends DefaultBaseDomain {

	private static final long serialVersionUID = 1L;

	private Double value;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;

	public FinancialRelease() {
		super();
	}

	public FinancialRelease(Long id, LocalDateTime creationDate, LocalDateTime updateDate, Double value, Owner owner) {
		super(id, creationDate, updateDate);
		this.value = value;
		this.owner = owner;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

}
