package com.locadora.jpa.model.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.locadora.jpa.model.domain.enums.Gender;
import com.locadora.jpa.model.util.converters.LocalDateConverter;
import com.locadora.jpa.vo.AlertsPerPerson;

@Entity
@Table(schema = "locadora", 
	indexes = {
			@Index(columnList = "cpf", unique = true), // Índice único
			@Index(columnList = "name, cpf", unique = true) // Índice composto
	}
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(columnDefinition = "person_type", discriminatorType = DiscriminatorType.STRING)

@NamedNativeQuery(name = "Person.alertsPerPerson",
	query = "select "
				+ "p.name as name, "
				+ "count(a.id) as alerts "
			+ "from "
				+ "locadora.Person p, "
				+ "locadora.Alert a "
			+ "where "
				+ "p.id = a.person_id "
			+ "group by p.id",
	resultSetMapping = "alertsPerPersonMapping"
)
@SqlResultSetMapping(name = "alertsPerPersonMapping",
	classes = {
			@ConstructorResult(
				targetClass = AlertsPerPerson.class, 
				columns = { 
						@ColumnResult(name = "name", type = String.class),
						@ColumnResult(name = "alerts", type = Long.class)
				}
			)
		}
)
public class Person extends DefaultBaseDomain {

	private static final long serialVersionUID = 1L;

	protected String name;

	// @Temporal só funciona com java.util.Date e java.util.Calendar
	@Temporal(TemporalType.DATE)
	protected Date birthDate;
	
	// Iremos utilizar Date no banco de dados, mas na aplicação iremos utilizar LocalDate.
	// Podemos converter atributos que irão variar entre o tipo que o banco irá tratar, e o tipo que a aplicação irá tratar.
	@Convert(converter = LocalDateConverter.class)
	protected LocalDate birthDateLocalDate;

	protected String cpf;

	@Enumerated(EnumType.ORDINAL)
	protected Gender gender;

	@ElementCollection
	@CollectionTable(name = "locadora.person_email", joinColumns = @JoinColumn(name = "person_id"), schema = "locadora")
	@Column(name = "email")
	private List<String> emails = new ArrayList<String>();

	@ElementCollection
	@CollectionTable(name = "locadora.person_phone", joinColumns = @JoinColumn(name = "person_id"), schema = "locadora")
	@AttributeOverrides({ @AttributeOverride(name = "number", column = @Column(name = "phone_number")) })
	private List<Phone> phones = new ArrayList<Phone>();

	@Lob
	private byte[] profilePicture;

	@Transient
	private String transientText = "Hello I'm a transient text";
	
	public Person() {
		super();
	}

	public Person(Long id, LocalDateTime creationDate, LocalDateTime updateDate, String name, Date birthDate,
			String cpf, Gender gender) {
		super(id, creationDate, updateDate);
		this.name = name;
		this.birthDate = birthDate;
		this.cpf = cpf;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getTransientText() {
		return transientText;
	}

	public void setTransientText(String transientText) {
		this.transientText = transientText;
	}

	public LocalDate getBirthDateLocalDate() {
		return birthDateLocalDate;
	}

	public void setBirthDateLocalDate(LocalDate birthDateLocalDate) {
		this.birthDateLocalDate = birthDateLocalDate;
	}

}
