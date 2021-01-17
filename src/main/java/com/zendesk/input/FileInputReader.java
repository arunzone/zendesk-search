package com.zendesk.input;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zendesk.entity.User;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

public class FileInputReader {
  private FileByPathReader fileByPathReader;

  public FileInputReader(FileByPathReader fileByPathReader) {
    this.fileByPathReader = fileByPathReader;
  }

  public List<User> usersFrom(String fileName) {
    try {
      return new JsonFactory(new ObjectMapper()).
          createParser(fileByPathReader.fileFromPath(fileName)).
          readValueAs(new TypeReference<List<User>>() {
          });
    } catch (IOException e) {
      throw new InvalidInputFileException(format("Unable to read content from file: %s", fileName));
    }
  }
}
