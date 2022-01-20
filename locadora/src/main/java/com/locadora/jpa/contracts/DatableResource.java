package com.locadora.jpa.contracts;

public interface DatableResource<ID> extends Datable, Resourceable<ID> {

	public abstract void prePersistPreUpdate();

}
