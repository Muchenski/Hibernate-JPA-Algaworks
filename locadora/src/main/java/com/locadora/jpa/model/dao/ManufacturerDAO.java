package com.locadora.jpa.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.locadora.jpa.contracts.ManufacturerModelContract;
import com.locadora.jpa.model.domain.Manufacturer;

@Repository
public class ManufacturerDAO extends GenericDAO<Manufacturer, Long> implements ManufacturerModelContract {

	public ManufacturerDAO() {
		super(Manufacturer.class);
	}

	@Override
	public Manufacturer findById(Long id) {
		String jpql = "select m from Manufacturer m left join fetch m.carModels c where m.id = ?1";
		Manufacturer result = entityManager.createQuery(jpql, clazz).setParameter(1, id).getSingleResult();
		return result;
	}

	@Override
	public List<Manufacturer> findAll() {
//		String jpql = "select distinct m from Manufacturer m left join fetch m.carModels c";
//		List<Manufacturer> results = entityManager.createQuery(jpql, clazz).getResultList();
//		return results;
		
		List<Manufacturer> results = entityManager.createNamedQuery("Manufacturer.all", Manufacturer.class).getResultList();
		return results;
	}
	
	@Override
	public List<Manufacturer> byNameLike(String name) {
		List<Manufacturer> results = entityManager.createNamedQuery("Manufacturer.byNameLike", Manufacturer.class).setParameter("name", "%" + name + "%").getResultList();
		return results;
	}
	
}
