package com.zendesk.cli.report;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class FieldExtractor {
  public <T> Set<ReportField> fieldsFor(T entity) {
    return stream(entity.getClass().getDeclaredFields())
        .map(field ->
        {
          field.setAccessible(true);
          String fieldName = fieldNameFrom(field);
          String fieldValue = fieldValueFrom(entity, field);
          return new ReportField(fieldName, fieldValue);
        }).collect(Collectors.toSet());
  }

  private <T> String fieldValueFrom(T entity, java.lang.reflect.Field field) {
    String fieldValue = null;
    try {
      Object value = field.get(entity);
      fieldValue = value != null ? value.toString() : "";
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return fieldValue;
  }

  private String fieldNameFrom(java.lang.reflect.Field field) {
    String fieldName = field.getName();
    if (field.isAnnotationPresent(JsonAlias.class)) {
      String[] annotationValues = field.getAnnotation(JsonAlias.class).value();
      Optional<String> firstValue = stream(annotationValues).findFirst();
      if (firstValue.isPresent()) {
        fieldName = firstValue.get();
      }
    }
    return fieldName;
  }
}
