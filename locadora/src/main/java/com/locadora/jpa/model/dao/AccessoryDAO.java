package com.locadora.jpa.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.locadora.jpa.contracts.AccessoryModelContract;
import com.locadora.jpa.model.domain.Accessory;

@Repository
public class AccessoryDAO extends GenericDAO<Accessory, Long> implements AccessoryModelContract {

	public AccessoryDAO() {
		super(Accessory.class);
	}

	@Override
	public List<Accessory> getAccessoriesByCar(Long carId) {
		String jpql = "select a from Car c left join c.accessories a where c.id = ?1";
		List<Accessory> results = entityManager.createQuery(jpql, clazz).setParameter(1, carId).getResultList();
		return results;
	}
	
	@Transactional
	@Override
	public Accessory update(Long id, Accessory t) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<Accessory> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(clazz);
		Root<Accessory> accessory = criteriaUpdate.from(clazz);
		criteriaUpdate
			.set("description", t.getDescription())
			.set("updateDate", LocalDateTime.now())
		.where(criteriaBuilder.equal(accessory.<Long>get("id"), id));
		Query query = entityManager.createQuery(criteriaUpdate);
		query.executeUpdate();
		return super.findById(id);
	}
	
	@Transactional
	@Override
	public void deleteById(Long id) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaDelete<Accessory> criteriaDelete = criteriaBuilder.createCriteriaDelete(clazz);
		Root<Accessory> accessory = criteriaDelete.from(clazz);
		criteriaDelete.where(criteriaBuilder.equal(accessory.<Long>get("id"), id));
		Query query = entityManager.createQuery(criteriaDelete);
		query.executeUpdate();
	}

}
