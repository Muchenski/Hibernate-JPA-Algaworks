package com.locadora.jpa.model.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.locadora.jpa.contracts.Crud;
import com.locadora.jpa.contracts.DatableResource;
import com.locadora.jpa.contracts.Resourceable;
import com.locadora.jpa.model.dao.GenericDAO;

@Service
public abstract class GenericService<T extends Resourceable<ID>, ID, D extends GenericDAO<T, ID>> implements Crud<T, ID> {

	protected D dao;

	protected Class<T> clazz;

	public GenericService(D dao, Class<T> clazz) {
		this.dao = dao;
		this.clazz = clazz;
		// clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T findById(ID id) {
		T result = dao.findById(id);
		if (result == null) {
			throw new IllegalArgumentException(clazz.getSimpleName() + " not found! Id = " + id);
		}
		return result;
	}

	@Override
	public List<T> findAll() {
		List<T> results = dao.findAll();
		return results;
	}

	@Override
	public T save(T t) {
		dao.save(t);
		t = findById(t.getId());
		return t;
	}

	@Override
	public T update(ID id, T t) {
		T target = findById(id);
		if (t instanceof DatableResource) {
			BeanUtils.copyProperties(t, target, "creationDate", "id");
		} else {
			BeanUtils.copyProperties(t, target, "id");
		}
		dao.update(id, target);
		target = findById(id);
		return target;
	}

	@Override
	public void delete(T t) {
		dao.delete(t);
	}

	@Override
	public void deleteById(ID id) {
		dao.deleteById(id);
	}

	@Override
	public void saveAll(List<T> ts) {
		dao.saveAll(ts);
	}

	@Override
	public Long totalRows() {
		return dao.totalRows();
	}

	@Override
	public List<T> find(int firstRow, int pageSize) {
		List<T> results = dao.find(firstRow, pageSize);
		return results;
	}
	
	@Override
	public List<T> filter(T entity, String... properties) {
		List<T> results = dao.filter(entity, properties);
		return results;
	}

}
