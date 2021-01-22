package com.zendesk.search;

import com.zendesk.entity.User;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExactMatchPredicateFactoryTest {

  @Test
  void shouldReturnTrueWhenGivenValueMatches() {
    User user = new User();
    user.setRole("admin");

    ExactMatchPredicateFactory factory = new ExactMatchPredicateFactory();

    boolean match = factory.match(user, "role", "admin");

    assertThat(match, is(true));
  }

  @Test
  void shouldReturnFalseWhenGivenValueNotMatched() {
    User user = new User();
    user.setRole("admin");

    ExactMatchPredicateFactory factory = new ExactMatchPredicateFactory();

    boolean match = factory.match(user, "role", "end-user");

    assertThat(match, is(false));
  }

  @Test
  void shouldThrowExceptionWhenGivenInvalidField() {
    User user = new User();
    user.setRole("admin");
    ExactMatchPredicateFactory factory = new ExactMatchPredicateFactory();

    InvalidFieldNameException invalidFieldNameException = assertThrows(InvalidFieldNameException.class, () -> factory.match(user, "idk", "end-user"));

    assertThat(invalidFieldNameException.getMessage(), is("Invalid field: idk"));
  }


}
