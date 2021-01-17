package com.zendesk.input;


import com.zendesk.entity.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileInputReaderTest {

  @Test
  void shouldReturnFirstUser() {
    FileByPathReader fileByPathReader = mock(FileByPathReader.class);
    FileInputReader reader = new FileInputReader(fileByPathReader);
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

    File userFile = new File("src/test/resources/users.json");
    when(fileByPathReader.fileFromPath("src/test/resources/users.json")).thenReturn(userFile);

    List<User> users = reader.usersFrom("src/test/resources/users.json");

    assertThat(users.stream().findFirst().get(), is(user));
  }

  @Test
  void shouldMatchRetrievedUsersCount() {
    FileByPathReader fileByPathReader = mock(FileByPathReader.class);
    FileInputReader reader = new FileInputReader(fileByPathReader);

    File userFile = new File("src/test/resources/users.json");
    when(fileByPathReader.fileFromPath("src/test/resources/users.json")).thenReturn(userFile);

    List<User> users = reader.usersFrom("src/test/resources/users.json");

    assertThat(users.size(), is(75));
  }

  @Test
  void shouldThrowExceptionForMismatchingInvalidInput() {
    FileByPathReader fileByPathReader = mock(FileByPathReader.class);
    FileInputReader reader = new FileInputReader(fileByPathReader);

    File userFile = new File(getClass().getClassLoader().getResource("tickets.json").getFile());
    when(fileByPathReader.fileFromPath("src/test/resources/users.json")).thenReturn(userFile);

    InvalidInputFileException invalidInputFileException = assertThrows(InvalidInputFileException.class, () -> {
      reader.usersFrom("src/test/resources/users.json");
    });

    assertThat(invalidInputFileException.getMessage(), is("Unable to read content from file: src/test/resources/users.json"));
  }
}
