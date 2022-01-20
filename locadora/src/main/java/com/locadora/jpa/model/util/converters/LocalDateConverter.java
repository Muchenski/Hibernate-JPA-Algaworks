package com.locadora.jpa.model.util.converters;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate attribute) {
		if (attribute == null)
			return null;
		return Date.from(attribute.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public LocalDate convertToEntityAttribute(Date dbData) {
		if (dbData == null)
			return null;
		return LocalDate.ofInstant(dbData.toInstant(), ZoneId.systemDefault());
	}

}
