package ecourse.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import ecourse.domain.BadgeLevel;

@Converter(autoApply=true)
public class BadgeLevelConverter implements AttributeConverter<BadgeLevel, String> {

	@Override
	public String convertToDatabaseColumn(BadgeLevel level) {
		switch (level) {
		case BRONZE:
			return "B";
		case SILVER:
			return "S";
		case GOLD:
			return "G";
		default:
			throw new IllegalArgumentException("Unknown value " + level);
		}
	}

	@Override
	public BadgeLevel convertToEntityAttribute(String dbData) {
		switch (dbData) {
		case "B":
			return BadgeLevel.BRONZE;
		case "S":
			return BadgeLevel.SILVER;
		case "G":
			return BadgeLevel.GOLD;
		default:
			throw new IllegalArgumentException("Unknown value " + dbData);
		}
	}
	
}