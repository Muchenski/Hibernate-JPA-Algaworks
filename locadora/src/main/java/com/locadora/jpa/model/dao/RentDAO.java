package com.locadora.jpa.model.dao;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.locadora.jpa.contracts.RentModelContract;
import com.locadora.jpa.model.domain.Car;
import com.locadora.jpa.model.domain.CarModel;
import com.locadora.jpa.model.domain.Rent;

@Repository
public class RentDAO extends GenericDAO<Rent, Long> implements RentModelContract {
	
	public RentDAO() {
		super(Rent.class);
	}

	@Override
	public List<Rent> getByDeliveryDateAndCarModel(Date deliveryDate, Long carModelId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Rent> criteriaQuery = criteriaBuilder.createQuery(clazz);
		
		Root<Rent> rent = criteriaQuery.from(clazz);
		
		criteriaQuery.select(rent);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(deliveryDate != null) {
			ParameterExpression<LocalDateTime> initDeliveryDate = criteriaBuilder.parameter(LocalDateTime.class, "initDeliveryDate");
			ParameterExpression<LocalDateTime> endDeliveryDate = criteriaBuilder.parameter(LocalDateTime.class, "endDeliveryDate");

			predicates.add(criteriaBuilder.between(rent.<LocalDateTime>get("deliveryDate"), initDeliveryDate, endDeliveryDate));
		}
		
		if(carModelId != null) {
			ParameterExpression<Long> carModelExpression = criteriaBuilder.parameter(Long.class, "carModelId");
			predicates.add(criteriaBuilder.equal(rent.<Car>get("car").<CarModel>get("carModel").<Long>get("id"), carModelExpression));
		}
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		
		TypedQuery<Rent> typedQuery = entityManager.createQuery(criteriaQuery);
		
		if(deliveryDate != null) {
			
			Calendar initDeliveryDate = Calendar.getInstance();
			initDeliveryDate.setTime(deliveryDate);
			initDeliveryDate.set(Calendar.HOUR_OF_DAY, 0);
			initDeliveryDate.set(Calendar.MINUTE, 0);
			initDeliveryDate.set(Calendar.SECOND, 0);
				
			Calendar endDeliveryDate = Calendar.getInstance();
			endDeliveryDate.setTime(deliveryDate);
			endDeliveryDate.set(Calendar.HOUR_OF_DAY, 23);
			endDeliveryDate.set(Calendar.MINUTE, 59);
			endDeliveryDate.set(Calendar.SECOND, 59);
				
			typedQuery.setParameter("initDeliveryDate", LocalDateTime.ofInstant(initDeliveryDate.toInstant(), ZoneId.systemDefault()));
			typedQuery.setParameter("endDeliveryDate", LocalDateTime.ofInstant(endDeliveryDate.toInstant(), ZoneId.systemDefault()));
		
		}
			
		if(carModelId != null) {
			typedQuery.setParameter("carModelId", carModelId);
		}
		
		return typedQuery.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Rent> getByDeliveryDateAndCarModelHibernate(Date deliveryDate, Long carModelId) {
		Session session = entityManager.getEntityManagerFactory().createEntityManager().unwrap(Session.class);
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(clazz);
		
		if(deliveryDate != null) {
			
			Calendar initDeliveryDate = Calendar.getInstance();
			initDeliveryDate.setTime(deliveryDate);
			initDeliveryDate.set(Calendar.HOUR_OF_DAY, 0);
			initDeliveryDate.set(Calendar.MINUTE, 0);
			initDeliveryDate.set(Calendar.SECOND, 0);
				
			Calendar endDeliveryDate = Calendar.getInstance();
			endDeliveryDate.setTime(deliveryDate);
			endDeliveryDate.set(Calendar.HOUR_OF_DAY, 23);
			endDeliveryDate.set(Calendar.MINUTE, 59);
			endDeliveryDate.set(Calendar.SECOND, 59);
			
			criteria.add(Restrictions.between("deliveryDate", LocalDateTime.ofInstant(initDeliveryDate.toInstant(), ZoneId.systemDefault()), LocalDateTime.ofInstant(endDeliveryDate.toInstant(), ZoneId.systemDefault())));
			
		}
		
		if(carModelId != null) {
			criteria.createAlias("car", "c");
			criteria.add(Restrictions.eq("c.carModel.id", carModelId));
		}
		
		return (List<Rent>) criteria.list();
	}

	@Override
	public Double getTotalRevenue() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Double> criteriaQuery = criteriaBuilder.createQuery(Double.class);
		
		Root<Rent> rent = criteriaQuery.from(clazz);
		
		criteriaQuery.select(criteriaBuilder.sum(rent.<Double>get("amount")));
		
		TypedQuery<Double> typedQuery = entityManager.createQuery(criteriaQuery);
		
		return typedQuery.getSingleResult();
	}

	@Override
	public Double getTotalRevenueOfMonthHibernate(int month) {
		Session session = entityManager.getEntityManagerFactory().createEntityManager().unwrap(Session.class);
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(clazz);
		
		criteria.setProjection(Projections.sum("amount"));
		
		criteria.add(Restrictions.sqlRestriction("extract(month from requestDate) = ?", month, StandardBasicTypes.INTEGER));
		
		return (Double) criteria.uniqueResult();
	}

}
