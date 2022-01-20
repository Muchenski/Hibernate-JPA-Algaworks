package com.locadora.jpa.exemplos_isolados.orphanRemoval;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.locadora.jpa.model.dao.GenericDAO;


@Repository
public class OwnerDAO extends GenericDAO<Owner, Long> {

	public OwnerDAO() {
		super(Owner.class);
	}

	@Override
	public Owner findById(Long id) {
		String jpql = "select o from Owner o left join fetch o.financialReleases f where o.id =:id";
		Owner result = entityManager.createQuery(jpql, clazz).setParameter("id", id).getSingleResult();
		return result;
	}

	@Override
	public List<Owner> findAll() {
		String jpql = "select distinct o from Owner o left join fetch o.financialReleases f";
		List<Owner> result = entityManager.createQuery(jpql, clazz).getResultList();
		return result;
	}
	
	@Transactional
	@Override
	public Owner save(Owner t) {
		t.getFinancialReleases().forEach(f -> f.setOwner(t));
		return super.save(t);
	}
	
	@Transactional
	@Override
	public Owner update(Long id, Owner t) {
		Owner target = findById(id);
		
		BeanUtils.copyProperties(t, target, "financialReleases", "id");
		
		target.getFinancialReleases().clear();
		target.getFinancialReleases().addAll(t.getFinancialReleases());
		target.getFinancialReleases().forEach(f -> f.setOwner(target));
		
		return super.update(id, target);
	}

}
