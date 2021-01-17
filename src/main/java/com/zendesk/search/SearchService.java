package com.zendesk.search;

import com.zendesk.entity.User;
import com.zendesk.repository.Repository;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SearchService {
  private final Repository repository;

  public SearchService(Repository repository) {
    this.repository = repository;
  }

  public List<User> findUsersBy(String fieldName, String value) {
    return repository.users().stream().filter(user ->
        new ExactMatchPredicateFactory().match(user, fieldName, value)
    ).collect(toList());
  }
}
