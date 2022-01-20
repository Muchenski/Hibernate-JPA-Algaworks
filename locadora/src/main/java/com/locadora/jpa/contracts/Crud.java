package com.locadora.jpa.contracts;

import java.util.List;

public interface Crud<T extends Resourceable<ID>, ID> {

	public abstract T findById(ID id);

	public abstract List<T> findAll();

	public abstract T save(T t);

	public abstract T update(ID id, T t);

	public abstract void delete(T t);
	
	public abstract void deleteById(ID id);

	public abstract void saveAll(List<T> ts);

	public abstract Long totalRows();

	public abstract List<T> find(int firstRow, int pageSize);
	
	public abstract List<T> filter(T entity, String... properties);

}
