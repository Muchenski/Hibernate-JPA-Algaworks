package com.locadora.jpa.seed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.locadora.jpa.exemplos_isolados.orphanRemoval.FinancialRelease;
import com.locadora.jpa.exemplos_isolados.orphanRemoval.Owner;
import com.locadora.jpa.exemplos_isolados.orphanRemoval.OwnerDAO;
import com.locadora.jpa.model.dao.AccessoryDAO;
import com.locadora.jpa.model.dao.CarDAO;
import com.locadora.jpa.model.dao.CarModelDAO;
import com.locadora.jpa.model.dao.ManufacturerDAO;
import com.locadora.jpa.model.dao.PersonDAO;
import com.locadora.jpa.model.dao.RentDAO;
import com.locadora.jpa.model.domain.Accessory;
import com.locadora.jpa.model.domain.Car;
import com.locadora.jpa.model.domain.CarModel;
import com.locadora.jpa.model.domain.Driver;
import com.locadora.jpa.model.domain.InsurancePolicy;
import com.locadora.jpa.model.domain.Manufacturer;
import com.locadora.jpa.model.domain.Person;
import com.locadora.jpa.model.domain.Rent;
import com.locadora.jpa.model.domain.enums.Category;
import com.locadora.jpa.model.domain.enums.Gender;

@Configuration
public class Seeding implements CommandLineRunner {

	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private ManufacturerDAO manufacturerDAO;

	@Autowired
	private CarModelDAO carModelDAO;

	@Autowired
	private CarDAO carDAO;

	@Autowired
	private AccessoryDAO accessoryDAO;

	@Autowired
	private RentDAO rentDAO;
	
	@Autowired
	private OwnerDAO ownerDAO;

	@Override
	public void run(String... args) throws Exception {
		
		LocalDateTime dateTime = LocalDateTime.now();
		Date date = new Date();
		
		Person driver1 = new Driver(null, dateTime, dateTime, "Henrique", date, "08645565005", "321321321", Gender.MASCULINE);
		Person driver2 = new Driver(null, dateTime, dateTime, "Lucas", date, "79764478077", "159159159", Gender.MASCULINE);
		Person driver3 = new Driver(null, dateTime, dateTime, "Matheus", date, "42154956017", "357357357", Gender.MASCULINE);

		driver1.setBirthDateLocalDate(LocalDate.now());
		
		personDAO.saveAll(List.of(driver1, driver2, driver3));
		
		Manufacturer audi = new Manufacturer(null, dateTime, dateTime, "Audi");
		Manufacturer mercedes = new Manufacturer(null, dateTime, dateTime, "Mercedes");
		Manufacturer bmw = new Manufacturer(null, dateTime, dateTime, "BMW");

		manufacturerDAO.saveAll(List.of(audi, mercedes, bmw));

		CarModel audiRS6 = new CarModel(null, dateTime, dateTime, "RS6", audi, Category.LARGE_SEDAN);
		CarModel audiA3 = new CarModel(null, dateTime, dateTime, "A3", audi, Category.COMPACT_HATCH);
		CarModel mercedesAMGGT = new CarModel(null, dateTime, dateTime, "AMG GT", mercedes, Category.SPORTING);
		CarModel mercedesGLECOUPE = new CarModel(null, dateTime, dateTime, "GLE COUPE", mercedes, Category.LARGE_SEDAN);
		CarModel bmwX5 = new CarModel(null, dateTime, dateTime, "X5", bmw, Category.LARGE_SEDAN);

		carModelDAO.saveAll(List.of(audiRS6, audiA3, mercedesAMGGT, mercedesGLECOUPE, bmwX5));

		audi.getCarModels().addAll(List.of(audiRS6, audiA3));
		mercedes.getCarModels().addAll(List.of(mercedesAMGGT, mercedesGLECOUPE));
		bmw.getCarModels().add(bmwX5);

		Accessory airBag = new Accessory(null, dateTime, dateTime, "Airbag");
		Accessory reverseSensor = new Accessory(null, dateTime, dateTime, "Reverse sensor");
		Accessory reverseCamera = new Accessory(null, dateTime, dateTime, "Reverse camera");
		Accessory parkAssist = new Accessory(null, dateTime, dateTime, "Park Assist");
		Accessory onboardComputer = new Accessory(null, dateTime, dateTime, "Onboard computer");
		Accessory autopilot = new Accessory(null, dateTime, dateTime, "autopilot");
		Accessory carRefrigerator = new Accessory(null, dateTime, dateTime, "Car refrigerator");

		accessoryDAO.saveAll(List.of(airBag, reverseSensor, reverseCamera, parkAssist, onboardComputer, autopilot, carRefrigerator));

		Car audiRS61 = new Car(null, dateTime, dateTime, "ABC-751", "321321321321", "blue", 100.0, audiRS6);
		Car audiRS62 = new Car(null, dateTime, dateTime, "AFE-157", "546546546464", "red", 220.0, audiRS6);
		Car audiA31 = new Car(null, dateTime, dateTime, "VDA-359", "595959595959", "violet", 150.0, audiA3);
		Car audiA32 = new Car(null, dateTime, dateTime, "AVG-176", "422424424242", "green", 310.0, audiA3);
		Car mercedesAMGGT1 = new Car(null, dateTime, dateTime, "GEG-145", "282828282828", "orange", 400.0, mercedesAMGGT);
		Car mercedesAMGGT2 = new Car(null, dateTime, dateTime, "PAA-753", "717171717171", "yellow", 435.0, mercedesAMGGT);
		Car mercedesGLECOUPE1 = new Car(null, dateTime, dateTime, "VLL-456", "656565656565", "blue", 398.0, mercedesGLECOUPE);
		Car mercedesGLECOUPE2 = new Car(null, dateTime, dateTime, "QWW-654", "747474747474", "white", 154.0, mercedesGLECOUPE);
		Car mercedesGLECOUPE3 = new Car(null, dateTime, dateTime, "PEA-159", "202020202020", "black", 365.0, mercedesGLECOUPE);
		Car bmwX51 = new Car(null, dateTime, dateTime, "LAC-321", "393939393939", "blue", 279.0, bmwX5);
		Car bmwX51Standard = new Car(null, dateTime, dateTime, "KJA-321", "393939393939", "blue", 279.0, bmwX5);

		audiRS61.getAccessories().addAll(List.of(airBag, reverseSensor, reverseCamera, parkAssist));
		audiRS62.getAccessories().addAll(List.of(airBag, reverseSensor, reverseCamera, parkAssist, onboardComputer, autopilot));
		audiA31.getAccessories().addAll(List.of(airBag, reverseSensor));
		audiA32.getAccessories().addAll(List.of(autopilot, carRefrigerator, onboardComputer));
		mercedesAMGGT1.getAccessories().addAll(List.of(airBag, reverseSensor, reverseCamera, parkAssist, onboardComputer, autopilot, carRefrigerator));
		mercedesAMGGT2.getAccessories().addAll(List.of(airBag, parkAssist, carRefrigerator));
		mercedesGLECOUPE1.getAccessories().addAll(List.of(airBag, parkAssist));
		mercedesGLECOUPE2.getAccessories().addAll(List.of(parkAssist, autopilot, carRefrigerator));
		mercedesGLECOUPE3.getAccessories().addAll(List.of(airBag, reverseSensor, reverseCamera));
		bmwX51.getAccessories().addAll(List.of(autopilot, carRefrigerator));

		carDAO.saveAll(List.of(audiRS61, audiRS62, audiA31, audiA32, mercedesAMGGT1, mercedesAMGGT2, mercedesGLECOUPE1, mercedesGLECOUPE2, mercedesGLECOUPE3, bmwX51, bmwX51Standard));

		Rent rent1 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 453.0, audiRS61, new InsurancePolicy(null, dateTime, dateTime, 305.0, true, true, true), (Driver) driver1);
		Rent rent2 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 144.0, audiRS61, new InsurancePolicy(null, dateTime, dateTime, 230.0, true, false, true), (Driver) driver1);
		Rent rent3 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 645.0, audiRS61, new InsurancePolicy(null, dateTime, dateTime, 125.0, false, true, true), (Driver) driver1);
		Rent rent4 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 373.0, audiRS62, new InsurancePolicy(null, dateTime, dateTime, 415.0, true, true, false),(Driver) driver1);
		Rent rent5 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 415.0, audiRS62, new InsurancePolicy(null, dateTime, dateTime, 530.0, true, false, true), (Driver) driver1);
		Rent rent6 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 832.0, audiA31, new InsurancePolicy(null, dateTime, dateTime, 385.0, false, true, true), (Driver) driver1);
		Rent rent7 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 123.0, audiA31, new InsurancePolicy(null, dateTime, dateTime, 245.0, true, false, true), (Driver) driver1);
		Rent rent8 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 345.0, audiA32, new InsurancePolicy(null, dateTime, dateTime, 205.0, true, true, false), (Driver) driver1);
		Rent rent9 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 453.0, audiA32, new InsurancePolicy(null, dateTime, dateTime, 158.0, true, false, false), (Driver) driver1);
		Rent rent10 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 90.0, mercedesAMGGT1, new InsurancePolicy(null, dateTime, dateTime, 330.0, true, false, true), (Driver) driver1);
		Rent rent11 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 97.0, mercedesAMGGT1, new InsurancePolicy(null, dateTime, dateTime, 908.0, false, true, true), (Driver) driver1);
		Rent rent12 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 85.0, mercedesAMGGT1, new InsurancePolicy(null, dateTime, dateTime, 785.0, true, false, true), (Driver) driver2);
		Rent rent13 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 88.0, mercedesAMGGT2, new InsurancePolicy(null, dateTime, dateTime, 358.0, false, false, true), (Driver) driver2);
		Rent rent14 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 647.0, mercedesGLECOUPE1, new InsurancePolicy(null, dateTime, dateTime, 347.0, false, true, true), (Driver) driver2);
		Rent rent15 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 376.0, mercedesGLECOUPE1, new InsurancePolicy(null, dateTime, dateTime, 954.0, true, false, true), (Driver) driver2);
		Rent rent16 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 543.0, mercedesGLECOUPE2, new InsurancePolicy(null, dateTime, dateTime, 254.0, true, true, true), (Driver) driver2);
		Rent rent17 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 453.0, mercedesGLECOUPE2, new InsurancePolicy(null, dateTime, dateTime, 178.0, true, true, false), (Driver) driver2);
		Rent rent18 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 176.0, mercedesGLECOUPE3, new InsurancePolicy(null, dateTime, dateTime, 369.0, true, true, true), (Driver) driver2);
		Rent rent19 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 359.0, mercedesGLECOUPE3, new InsurancePolicy(null, dateTime, dateTime, 487.0, true, false, true), (Driver) driver3);
		Rent rent20 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 456.0, mercedesGLECOUPE3, new InsurancePolicy(null, dateTime, dateTime, 300.0, true, true, true), (Driver) driver3);
		Rent rent21 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 369.0, mercedesGLECOUPE3, new InsurancePolicy(null, dateTime, dateTime, 300.0, true, true, true), (Driver) driver3);
		Rent rent22 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 147.0, bmwX51, new InsurancePolicy(null, dateTime, dateTime, 300.0, true, true, false), (Driver) driver3);
		Rent rent23 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 258.0, bmwX51, new InsurancePolicy(null, dateTime, dateTime, 300.0, false, true, true), (Driver) driver3);
		Rent rent24 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 852.0, bmwX51, new InsurancePolicy(null, dateTime, dateTime, 300.0, false, false, true), (Driver) driver3);
		Rent rent25 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 953.0, bmwX51, new InsurancePolicy(null, dateTime, dateTime, 300.0, true, true, true), (Driver) driver3);
		Rent rent26 = new Rent(null, dateTime, dateTime, dateTime, dateTime, dateTime, 267.0, bmwX51, new InsurancePolicy(null, dateTime, dateTime, 300.0, false, true, false), (Driver) driver3);

		rentDAO.saveAll(List.of(rent1, rent2, rent3, rent4, rent5, rent6, rent7, rent8, rent9, rent10, rent11, rent12,
				rent13, rent14, rent15, rent16, rent17, rent18, rent19, rent20, rent21, rent22, rent23, rent24, rent25,
				rent26));
		
		Owner owner = new Owner(null, null, null, "Henrique");

		FinancialRelease f1 = new FinancialRelease(null, null, null, 500.0, owner);
		FinancialRelease f2 = new FinancialRelease(null, null, null, 800.0, owner);
		FinancialRelease f3 = new FinancialRelease(null, null, null, 240.0, owner);
		
		owner.setFinancialReleases(List.of(f1, f2, f3));
		
		ownerDAO.save(owner);

	}

}
