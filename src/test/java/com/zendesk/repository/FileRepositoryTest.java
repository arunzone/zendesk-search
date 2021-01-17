package com.zendesk.repository;

import com.zendesk.input.FileInputReader;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class FileRepositoryTest {

  @Test
  void shouldRetrieveUsersFromDefaultTestFile() {
    FileInputReader fileInputReader = mock(FileInputReader.class);
    FileRepository repository = new FileRepository(fileInputReader);

    repository.users();

    verify(fileInputReader).usersFrom("src/test/resources/users.json");
  }

  @Test
  void shouldRetrieveUsersFromGivenFile() {
    FileInputReader fileInputReader = mock(FileInputReader.class);
    FileRepository repository = new FileRepository(fileInputReader);
    repository.setUserInputFileName("/tmp/users.json");

    repository.users();

    verify(fileInputReader).usersFrom("/tmp/users.json");
  }
}
