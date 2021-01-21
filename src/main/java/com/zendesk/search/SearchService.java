package com.zendesk.search;

import com.zendesk.repository.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SearchService {
  private final Repository repository;

  public SearchService(Repository repository) {
    this.repository = repository;
  }

  public <T> List<T> findEntitiesBy(String fieldName, String value) {
    List<T> users = repository.entities();
    if (value.isEmpty()) {
      return users;
    }
    return users.stream().filter(user ->
        new ExactMatchPredicateFactory<T>().match(user, fieldName, value)
    ).collect(toList());
  }
}
