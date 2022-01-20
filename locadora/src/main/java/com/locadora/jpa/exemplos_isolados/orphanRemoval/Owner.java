package com.locadora.jpa.exemplos_isolados.orphanRemoval;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.locadora.jpa.model.domain.DefaultBaseDomain;

@Entity
@Table(schema = "locadora")
public class Owner extends DefaultBaseDomain {

	private static final long serialVersionUID = 1L;

	private String name;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FinancialRelease> financialReleases = new ArrayList<FinancialRelease>();

	public Owner() {
		super();
	}

	public Owner(Long id, LocalDateTime creationDate, LocalDateTime updateDate, String name) {
		super(id, creationDate, updateDate);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FinancialRelease> getFinancialReleases() {
		return financialReleases;
	}

	public void setFinancialReleases(List<FinancialRelease> financialReleases) {
		this.financialReleases = financialReleases;
	}

}