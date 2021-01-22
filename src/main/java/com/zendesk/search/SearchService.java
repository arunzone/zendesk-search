package com.zendesk.search;

import com.zendesk.repository.Repository;

import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

public class SearchService {
  private final Repository repository;

  public SearchService(Repository repository) {
    this.repository = repository;
  }

  public <T> List<T> findEntitiesBy(String fieldName, String value) {
    List<T> entities = repository.entities();
    if (value.isEmpty()) {
      return entities;
    }
    return findBy(fieldName, value, entities);
  }

  private <T> List<T> findBy(String fieldName, String value, List<T> entities) {
    String associatedFieldName = associated(fieldName, entities);
    if (associatedFieldName == null) {
      throw new InvalidFieldNameException(format("Invalid field name: %s", fieldName));
    }
    return entities.stream().filter(entity ->
        new ExactMatchPredicateFactory<T>().match(entity, associatedFieldName, value)
    ).collect(toList());
  }

  private <T> String associated(String fieldName, List<T> entities) {
    Class entityClass = entities.stream().findFirst().get().getClass();
    return FieldNameAssociationFactory.fieldNameMapFor(entityClass).get(fieldName);
  }


}
