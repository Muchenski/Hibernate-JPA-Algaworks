package com.locadora.jpa.model.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(schema = "locadora")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
// NONE - Nenhuma estratégia de cache.
// NONSTRICT_READ_WRITE - Significa que eventualmente irá atualizar estes dados. 
// READ_ONLY - Nunca irá atualizar.
public class Manufacturer extends DefaultBaseDomain {

	private static final long serialVersionUID = 1L;

	private String name;

	@OneToMany(mappedBy = "manufacturer" /* , fetch = FetchType.EAGER */)
	private List<CarModel> carModels = new ArrayList<CarModel>();

	public Manufacturer() {
		super();
	}

	public Manufacturer(Long id, LocalDateTime creationDate, LocalDateTime updateDate, String name) {
		super(id, creationDate, updateDate);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CarModel> getCarModels() {
		return carModels;
	}

	public void setCarModels(List<CarModel> carModels) {
		this.carModels = carModels;
	}

}
