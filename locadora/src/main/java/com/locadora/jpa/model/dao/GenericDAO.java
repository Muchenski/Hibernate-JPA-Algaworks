package com.locadora.jpa.model.dao;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.locadora.jpa.contracts.Crud;
import com.locadora.jpa.contracts.Resourceable;

@Repository
public abstract class GenericDAO<T extends Resourceable<ID>, ID> implements Crud<T, ID> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected Class<T> clazz;

	public GenericDAO(Class<T> clazz) {
		super();
		this.clazz = clazz;
	}

	@Override
	public T findById(ID id) {
		T result = entityManager.find(clazz, id);
		return result;
	}

	@Override
	public List<T> findAll() {
		List<T> results = entityManager.createQuery("from " + clazz.getSimpleName(), clazz).getResultList();
		return results;
	}

	@Transactional
	@Override
	public T save(T t) {
		entityManager.persist(t);
		return t;
	}

	@Transactional
	@Override
	public T update(ID id, T t) {
		try {
			t = entityManager.merge(t);
			return t;
		} catch(OptimisticLockException e) {
			throw new IllegalStateException("This entity has been saved!");
		} catch(Exception e) {
			throw new DataIntegrityViolationException(e.getMessage());	
		}
	}

	@Transactional
	@Override
	public void delete(T t) {
		try {
			deleteById(t.getId());
		} catch(Exception e) {
			throw new DataIntegrityViolationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void deleteById(ID id) {
		try {
			entityManager.remove(findById(id));
			entityManager.flush();
		} catch(Exception e) {
			throw new DataIntegrityViolationException(e.getMessage());
		}
	}
	
	@Transactional
	@Override
	public void saveAll(List<T> ts) {
		ts.forEach(t -> save(t));
	}
	
	@Override
	public Long totalRows() {
		Long rows = entityManager.createQuery("select count(t) from " + clazz.getSimpleName() + " t", Long.class).getSingleResult();
		return rows;
	}
	
	@Override
	public List<T> find(int firstRow, int pageSize) {
		List<T> results = entityManager.createQuery("from " + clazz.getSimpleName(), clazz).setFirstResult(firstRow).setMaxResults(pageSize).getResultList();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> filter(T entity, String... properties) {
		Session session = entityManager.getEntityManagerFactory().createEntityManager().unwrap(Session.class);
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(clazz);
		
		if(properties != null) {
			for (String property : properties) {
				try {
					Field field = entity.getClass().getDeclaredField(property);
					field.setAccessible(true);
					Object value = field.get(entity);
					if(value != null) {
						if(value instanceof String) {
							criteria.add(Restrictions.ilike(property, (String) value, MatchMode.ANYWHERE));
						} else {
							criteria.add(Restrictions.eq(property, value));
						}
					}
				} catch (Exception e) {
					throw new IllegalArgumentException("Property not found! " + property);
				}
			}
		}
		
		return criteria.list();
	}
	
}
