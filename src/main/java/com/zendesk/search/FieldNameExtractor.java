package com.zendesk.search;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class FieldNameExtractor {
  public <T> List<String> fieldNamesOf(Class<T> aClass) {
    return stream(aClass.getDeclaredFields()).map(this::fieldNameOf).collect(toList());
  }

  public String fieldNameOf(java.lang.reflect.Field field) {
    if (field.isAnnotationPresent(JsonAlias.class)) {
      String[] annotationValues = field.getAnnotation(JsonAlias.class).value();
      Optional<String> firstValue = stream(annotationValues).findFirst();
      if (firstValue.isPresent()) {
        return firstValue.get();
      }
    }
    return field.getName();
  }
}
