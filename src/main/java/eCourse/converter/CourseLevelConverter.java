package ecourse.converter;

import ecourse.domain.CourseLevel;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CourseLevelConverter implements AttributeConverter<CourseLevel, String> {

  @Override
  public String convertToDatabaseColumn(CourseLevel level) {
    switch (level) {
      case BEGINNER:
        return "B";
      case INTERMEDIATE:
        return "I";
      case ADVANCED:
        return "A";
      default:
        throw new IllegalArgumentException("Unknown value " + level);
    }
  }

  @Override
  public CourseLevel convertToEntityAttribute(String dbData) {
    switch (dbData) {
      case "B":
        return CourseLevel.BEGINNER;
      case "I":
        return CourseLevel.INTERMEDIATE;
      case "A":
        return CourseLevel.ADVANCED;
      default:
        throw new IllegalArgumentException("Unknown value " + dbData);
    }
  }

}
