package com.locadora.jpa.contracts;

import java.io.Serializable;

public interface Resourceable<ID> extends Serializable {

	public abstract ID getId();

	public abstract void setId(ID id);

}
