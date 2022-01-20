package com.locadora.jpa.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

import com.locadora.jpa.contracts.Resourceable;

public class DefaultResourceableDTO extends DefaultDTO implements Resourceable<Long> {

	private static final long serialVersionUID = 1L;

	protected Long id;

	public DefaultResourceableDTO() {
		super();
	}

	public DefaultResourceableDTO(LocalDateTime creationDate, LocalDateTime updateDate, Long id) {
		super(creationDate, updateDate);
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
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
		DefaultResourceableDTO other = (DefaultResourceableDTO) obj;
		return Objects.equals(id, other.id);
	}

}
