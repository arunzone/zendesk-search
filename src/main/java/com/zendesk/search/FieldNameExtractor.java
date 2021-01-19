package com.zendesk.search;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class FieldNameExtractor<T> {
  public List<String> fieldNamesOf(Class<T> aClass) {
    return stream(aClass.getDeclaredFields()).map(field ->
    {
      if (field.isAnnotationPresent(JsonAlias.class)) {
        String[] annotationValues = field.getAnnotation(JsonAlias.class).value();
        Optional<String> firstValue = stream(annotationValues).findFirst();
        if (firstValue.isPresent()) {
          return firstValue.get();
        }
      }
      return field.getName();
    }).collect(toList());
  }
}
