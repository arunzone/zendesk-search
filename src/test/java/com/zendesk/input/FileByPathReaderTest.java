package com.zendesk.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Read file by input location")
class FileByPathReaderTest {

  @Test
  void shouldReturnValidFile() {
    File inputFile = new File("src/test/resources/users.json");
    FileByPathReader reader = new FileByPathReader();

    File file = reader.fileFromPath(inputFile.getAbsolutePath());

    assertThat(file.isFile(), is(true));
  }

  @Test
  void shouldThrowExceptionWhenNotAFile() {
    File inputFile = new File("src/test/resources");
    FileByPathReader reader = new FileByPathReader();

    InvalidInputFileException invalidInputFileException = assertThrows(InvalidInputFileException.class, () -> {
      reader.fileFromPath(inputFile.getAbsolutePath());
    });

    assertThat(invalidInputFileException.getMessage(), endsWith("src/test/resources"));
  }

  @Test
  void shouldThrowExceptionForInvalidPath() {
    FileByPathReader reader = new FileByPathReader();

    InvalidInputFileException invalidInputFileException = assertThrows(InvalidInputFileException.class, () -> {
      reader.fileFromPath("unavailable.json");
    });

    assertThat(invalidInputFileException.getMessage(), is("Invalid file location: unavailable.json"));
  }
}
