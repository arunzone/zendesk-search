package com.zendesk.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zendesk.input.FileInputReader;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class FileRepositoryTest {

  @Test
  void shouldRetrieveUsersFromDefaultTestFile() {
    FileInputReader fileInputReader = mock(FileInputReader.class);
    FileRepository repository = new FileRepository(fileInputReader);

    repository.users();

    verify(fileInputReader).entitiesFrom(eq("src/test/resources/users.json"), any(TypeReference.class));
  }

  @Test
  void shouldRetrieveUsersFromGivenFile() {
    FileInputReader fileInputReader = mock(FileInputReader.class);
    FileRepository repository = new FileRepository(fileInputReader);
    repository.setUserInputFileName("/tmp/users.json");

    repository.users();

    verify(fileInputReader).entitiesFrom(eq("/tmp/users.json"), any(TypeReference.class));
  }
}
