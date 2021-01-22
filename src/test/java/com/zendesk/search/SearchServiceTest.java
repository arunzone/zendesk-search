package com.zendesk.search;

import com.zendesk.entity.User;
import com.zendesk.repository.Repository;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class SearchServiceTest {

  @Mock
  private Repository repository;

  private SearchService searchService;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    searchService = new SearchService(repository);
  }

  @Test
  void shouldReturnMatchingUserById() {
    when(repository.entities()).thenReturn(List.of(userFrancisca(), userRose()));

    List<User> users = searchService.findEntitiesBy("_id", "4");

    assertThat(users, contains(userRose()));
  }

  @Test
  void shouldReturnMatchingUserByName() {
    when(repository.entities()).thenReturn(List.of(userFrancisca(), userRose()));

    List<User> users = searchService.findEntitiesBy("name", "Francisca Rasmussen");

    assertThat(users, contains(userFrancisca()));
  }

  @Test
  void shouldReturnMatchingUserBySharedFlag() {
    when(repository.entities()).thenReturn(List.of(userFrancisca(), userRose()));

    List<User> users = searchService.findEntitiesBy("shared", "false");

    assertThat(users, contains(userFrancisca()));
  }

  @Test
  void shouldReturnMatchingUserByTag() {
    when(repository.entities()).thenReturn(List.of(userFrancisca(), userRose()));

    List<User> users = searchService.findEntitiesBy("tags", "Rowe");

    assertThat(users, contains(userRose()));
  }

  @Test
  void shouldReturnMatchingAllUsersByActiveState() {
    when(repository.entities()).thenReturn(List.of(userFrancisca(), userRose()));

    List<User> users = searchService.findEntitiesBy("active", "true");

    assertThat(users, contains(userFrancisca(), userRose()));
  }

  @Test
  void shouldReturnAllUsersOnEmptyInput() {
    when(repository.entities()).thenReturn(List.of(userFrancisca(), userRose()));

    List<User> users = searchService.findEntitiesBy("active", "");

    assertThat(users, contains(userFrancisca(), userRose()));
  }

  @Test
  void shouldReturnEmptyListWhenNotMatched() {
    when(repository.entities()).thenReturn(List.of(userFrancisca(), userRose()));

    List<User> users = searchService.findEntitiesBy("name", "Henry Rasmussen");

    assertThat(users, is(empty()));
  }

  @Test
  void shouldReturnEmptyListForUnknownField() {
    when(repository.entities()).thenReturn(List.of(userFrancisca(), userRose()));

    InvalidFieldNameException invalidFieldNameException = assertThrows(InvalidFieldNameException.class, () -> searchService.findEntitiesBy("idk", "Henry Rasmussen"));

    assertThat(invalidFieldNameException.getMessage(), Is.is("Invalid field name: idk"));
  }

  private User userFrancisca() {
    User user = new User();
    user.setId(1L);
    user.setUrl("http://initech.zendesk.com/api/v2/users/1.json");
    user.setExternalId("74341f74-9c79-49d5-9611-87ef9b6eb75f");
    user.setName("Francisca Rasmussen");
    user.setAlias("Miss Coffey");
    user.setCreatedAt("2016-04-15T05:19:46 -10:00");
    user.setActive(true);
    user.setVerified(true);
    user.setShared(false);
    user.setLocale("en-AU");
    user.setTimezone("Sri Lanka");
    user.setLastLoginAt("2013-08-04T01:03:27 -10:00");
    user.setEmail("coffeyrasmussen@flotonic.com");
    user.setPhone("8335-422-718");
    user.setSignature("Don't Worry Be Happy!");
    user.setOrganizationId(119L);
    user.setTags(List.of("Springville", "Sutton", "Hartsville/Hartley", "Diaperville"));
    user.setSuspended(true);
    user.setRole("admin");
    return user;
  }

  private User userRose() {
    User user = new User();
    user.setId(4L);
    user.setUrl("http://initech.zendesk.com/api/v2/users/4.json");
    user.setExternalId("37c9aef5-cf01-4b07-af24-c6c49ac1d1c7");
    user.setName("Rose Newton");
    user.setAlias("Mr Cardenas");
    user.setCreatedAt("2016-02-09T07:52:10 -11:00");
    user.setActive(true);
    user.setVerified(true);
    user.setShared(true);
    user.setLocale("de-CH");
    user.setTimezone("Netherlands");
    user.setLastLoginAt("2012-09-25T01:32:46 -10:00");
    user.setEmail("cardenasnewton@flotonic.com");
    user.setPhone("8685-482-450");
    user.setSignature("Don't Worry Be Happy!");
    user.setOrganizationId(122L);
    user.setTags(List.of("Gallina", "Glenshaw", "Rowe", "Babb"));
    user.setSuspended(true);
    user.setRole("end-user");
    return user;
  }
}
