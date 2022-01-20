package com.locadora.jpa.model.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.locadora.jpa.contracts.DatableResource;

@Entity
@Table(schema = "locadora")
@Inheritance(strategy = InheritanceType.JOINED)
public class DefaultBaseDomain implements DatableResource<Long> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	protected LocalDateTime creationDate;

	protected LocalDateTime updateDate;

	public DefaultBaseDomain() {
		super();
	}

	public DefaultBaseDomain(Long id, LocalDateTime creationDate, LocalDateTime updateDate) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	// Callbacks do ciclo de vida e estados de objetos de classes mapeadas pelo JPA.

	// @PrePersist
	// @PostPersist
	
	// @PreUpdate
	// @PostUpdate
	
	// @PreRemove
	// @PostRemove

	@PrePersist
	@PreUpdate
	@Override
	public void prePersistPreUpdate() {
		if (this.creationDate == null) {
			this.creationDate = LocalDateTime.now();
		}
		this.updateDate = LocalDateTime.now();
	}

	@Override
	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	@Override
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	@Override
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefaultBaseDomain other = (DefaultBaseDomain) obj;
		return Objects.equals(id, other.id);
	}

}
