package com.locadora.jpa.contracts;

import java.time.LocalDateTime;

public interface Datable {

	public abstract LocalDateTime getCreationDate();

	public abstract void setCreationDate(LocalDateTime creationDate);

	public abstract LocalDateTime getUpdateDate();

	public abstract void setUpdateDate(LocalDateTime updateDate);
	
}
