package com.zendesk.repository;

import com.zendesk.entity.User;
import com.zendesk.input.FileInputReader;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class FileRepositoryTest {

  @Test
  void shouldRetrieveUsersFromDefaultTestFile() {
    FileInputReader fileInputReader = mock(FileInputReader.class);
    FileRepository repository = new FileRepository(fileInputReader, "src/test/resources/users.json");

    repository.entities();

    verify(fileInputReader).entitiesFrom("src/test/resources/users.json", User.class);
  }

  @Test
  void shouldRetrieveUsersFromGivenFile() {
    FileInputReader fileInputReader = mock(FileInputReader.class);
    FileRepository repository = new FileRepository(fileInputReader, "src/test/resources/users.json");
    repository.setUserInputFileName("/tmp/users.json");

    repository.entities();

    verify(fileInputReader).entitiesFrom("/tmp/users.json", User.class);
  }
}
