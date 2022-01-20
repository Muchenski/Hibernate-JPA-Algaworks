package com.locadora.jpa.model.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.locadora.jpa.contracts.CarModelContract;
import com.locadora.jpa.dtos.car.CarGetDTO;
import com.locadora.jpa.model.domain.Car;
import com.locadora.jpa.model.domain.CarModel;
import com.locadora.jpa.model.domain.Rent;
import com.locadora.jpa.vo.DailyValueAndLicensePlate;
import com.locadora.jpa.vo.RentalValuePerCar;

@Repository
public class CarDAO extends GenericDAO<Car, Long> implements CarModelContract {

	public CarDAO() {
		super(Car.class);
	}
	
	// Melhorando a performance com o join fetch
	@Override
	public List<Car> findAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(clazz);
		
		Root<Car> car = criteriaQuery.from(clazz);

		// car.<Car, CarModel>join("carModel", JoinType.LEFT); -> Não realiza o fetch junto.
		
		car.<Car, CarModel>fetch("carModel", JoinType.LEFT); // Realiza o join fetch
		
		criteriaQuery.select(car);
		
		TypedQuery<Car> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<Car> results = typedQuery.getResultList();
		
		return results;
	}

	@Override
	public List<Object[]> getCarReport() {
		String jpql = 
				"select "
					+ "c.id, " 
					+ "c.licensePlate, " 
					+ "count(r), " 
					+ "max(r.amount), " 
					+ "avg(r.amount) "
				+ "from Car c "
				+ "left join c.rents r " 
					+ "group by c.id " 
					+ "having count(r) > 1"
		;
		List<Object[]> results = entityManager.createQuery(jpql, Object[].class).getResultList();
		return results;
	}

	@Override
	public List<String> getLicensePlates() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		// Criando a query e tipando seu retorno.
		CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
		
		// Informando a tabela da entidade que será realizada a consulta.
		Root<Car> car = criteriaQuery.from(clazz);
		
		// Informando a(s) coluna(s) da tabela/atributo(s) da classe que iremos selecionar.
		criteriaQuery.select(car.<String>get("licensePlate"));
		
		TypedQuery<String> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<String> results = typedQuery.getResultList();
		
		return results;
	}
	
	@Override
	public List<DailyValueAndLicensePlate> getDailyValueAndLicensePlate() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DailyValueAndLicensePlate> criteriaQuery = criteriaBuilder.createQuery(DailyValueAndLicensePlate.class);
		
		Root<Car> car = criteriaQuery.from(clazz);
		
		criteriaQuery.select(
				criteriaBuilder.construct(DailyValueAndLicensePlate.class,
						car.<Double>get("dailyValue"),
						car.<String>get("licensePlate")
				)
		);

		TypedQuery<DailyValueAndLicensePlate> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<DailyValueAndLicensePlate> results = typedQuery.getResultList();
		
		return results;
	}
	
	@Deprecated
	public List<DailyValueAndLicensePlate> getDailyValueAndLicensePlate2() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
		
		Root<Car> car = criteriaQuery.from(clazz);
		
		criteriaQuery.multiselect(car.<Double>get("dailyValue").alias("dailyValue"), car.<String>get("licensePlate").alias("licensePlate"));

		TypedQuery<Tuple> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<Tuple> results = typedQuery.getResultList();
		
		return results.stream().map(t -> new DailyValueAndLicensePlate(Double.parseDouble(t.get(0).toString()), t.get("licensePlate").toString())).collect(Collectors.toList());
	}

	@Deprecated
	public List<Object[]> getDailyValueAndLicensePlate1() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		
		Root<Car> car = criteriaQuery.from(clazz);
		
		criteriaQuery.multiselect(car.<Double>get("dailyValue").alias("dv"), car.<String>get("licensePlate").alias("lp"));

		TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<Object[]> results = typedQuery.getResultList();
		
		return results;
	}

	@Override
	public List<CarGetDTO> getCarByColorEquals(String searchColor) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CarGetDTO> criteriaQuery = criteriaBuilder.createQuery(CarGetDTO.class);
		
		Root<Car> car = criteriaQuery.from(clazz);
		
		criteriaQuery.select(
				criteriaBuilder.construct(CarGetDTO.class,
						car.<LocalDateTime>get("creationDate"),
						car.<LocalDateTime>get("updateDate"),
						car.<Long>get("id"),
						car.<String>get("licensePlate"),
						car.<String>get("chassis"),
						car.<String>get("color"),
						car.<String>get("dailyValue"),
						car.<Long>get("carModel").get("id"),
						car.<String>get("carModel").get("description")
				)
		);
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		if(searchColor != null) {
			Predicate predicate = criteriaBuilder.equal(criteriaBuilder.upper(car.<String>get("color")), searchColor.toUpperCase());
			predicates.add(predicate);
		}
		
		Order order = criteriaBuilder.asc(car.get("dailyValue"));
		
		criteriaQuery.where(predicates.toArray(new Predicate[0]));
		criteriaQuery.orderBy(order);
		
		TypedQuery<CarGetDTO> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<CarGetDTO> results = typedQuery.getResultList();
		
		return results;
	}

	@Override
	public List<CarGetDTO> getCarsWithADailyRateGreaterThanOrEqualToTheAverage() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<CarGetDTO> criteriaQuery = criteriaBuilder.createQuery(CarGetDTO.class);
		Subquery<Double> subquery = criteriaQuery.subquery(Double.class);
		
		Root<Car> car = criteriaQuery.from(clazz);
		Root<Car> carSubquery = subquery.from(clazz);
		
		criteriaQuery.select(
				criteriaBuilder.construct(CarGetDTO.class,
						car.<LocalDateTime>get("creationDate"),
						car.<LocalDateTime>get("updateDate"),
						car.<Long>get("id"),
						car.<String>get("licensePlate"),
						car.<String>get("chassis"),
						car.<String>get("color"),
						car.<String>get("dailyValue"),
						car.<Long>get("carModel").get("id"),
						car.<String>get("carModel").get("description")
				)
		);
		subquery.select(criteriaBuilder.avg(carSubquery.<Double>get("dailyValue")));
		
		criteriaQuery.where(criteriaBuilder.greaterThanOrEqualTo(car.<Double>get("dailyValue"), subquery));
		
		TypedQuery<CarGetDTO> typedQuery = entityManager.createQuery(criteriaQuery);
		
		List<CarGetDTO> results = typedQuery.getResultList();
		
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getNeverRentedCarsHibernate() {
		/*
		 * select * from 
		 * 		locadora.car 
		 * where 
		 * 		id not in
		 * 			(select car_id from 
		 * 				locadora.rent 
		 * 			where 
		 * 				car_id is not null)
		 * 
		*/
		
		Session session = entityManager.getEntityManagerFactory().createEntityManager().unwrap(Session.class);
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Car.class);
		
		DetachedCriteria criteriaRent = DetachedCriteria.forClass(Rent.class);
		
		criteriaRent.setProjection(Projections.property("car.id"));
		criteriaRent.add(Restrictions.isNotNull("car.id"));
		
		criteria.add(Property.forName("id").notIn(criteriaRent));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RentalValuePerCar> totalRentalValuePerCarHibernate() {
		/*
		 * select 
		 *		car.licensePlate, 
		 *		count(rent.id),
		 *		sum(rent.amount)	
		 *	from locadora.car
		 *		left join locadora.rent
		 *		on rent.car_id = car.id
		 * group by car.id
		 * order by rents desc;
		 */
		
		Session session = entityManager.getEntityManagerFactory().createEntityManager().unwrap(Session.class);
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Car.class);
		
		criteria.createAlias("rents", "r");
		
		ProjectionList projections = Projections.projectionList()
				.add(Projections.groupProperty("licensePlate").as("licensePlate"))
				.add(Projections.count("r.id").as("rents"))
				.add(Projections.sum("r.amount").as("totalValue"));
		
		criteria.setProjection(projections);
		criteria.addOrder(org.hibernate.criterion.Order.desc("rents"));
		criteria.setResultTransformer(Transformers.aliasToBean(RentalValuePerCar.class));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Car> getCarsOfManufacturerHibernate(String manufacturerName) {
		Session session = entityManager.getEntityManagerFactory().createEntityManager().unwrap(Session.class);
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(Car.class);
		
		criteria.createAlias("carModel", "cm");
		criteria.createAlias("cm.manufacturer", "m");
		criteria.add(Restrictions.ilike("m.name", manufacturerName, MatchMode.ANYWHERE));
		
		return criteria.list();
	}
	
}
