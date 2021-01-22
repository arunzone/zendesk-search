package com.zendesk.search;

import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toUnmodifiableMap;

public class FieldNameMapper {
  private final FieldNameExtractor fieldNameExtractor;

  public FieldNameMapper(FieldNameExtractor fieldNameExtractor) {
    this.fieldNameExtractor = fieldNameExtractor;
  }

  public <T> Map<String, String> mappedFieldNamesFor(Class<T> aClass) {
    return stream(aClass.getDeclaredFields()).collect(toUnmodifiableMap(field -> fieldNameExtractor.fieldNameOf(field), field -> field.getName()));
  }
}
