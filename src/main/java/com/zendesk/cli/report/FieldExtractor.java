package com.zendesk.cli.report;

import com.zendesk.search.FieldNameExtractor;
import com.zendesk.search.exception.InvalidFieldException;

import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class FieldExtractor {
  private final FieldNameExtractor fieldNameExtractor;

  public FieldExtractor(FieldNameExtractor fieldNameExtractor) {
    this.fieldNameExtractor = fieldNameExtractor;
  }

  public <T> Set<ReportField> fieldsFor(T entity) {
    return stream(entity.getClass().getDeclaredFields())
        .map(field ->
        {
          field.setAccessible(true);
          String fieldName = fieldNameExtractor.fieldNameOf(field);
          String fieldValue = fieldValueFrom(entity, field);
          return new ReportField(fieldName, fieldValue);
        }).collect(Collectors.toSet());
  }

  <T> String fieldValueFrom(T entity, java.lang.reflect.Field field) {
    try {
      Object value = field.get(entity);
      return value != null ? value.toString() : "";
    } catch (IllegalAccessException e) {
      throw new InvalidFieldException("Problem accessing field value");
    }
  }

}
