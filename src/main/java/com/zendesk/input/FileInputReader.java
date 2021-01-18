package com.zendesk.input;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static java.lang.String.format;

public class FileInputReader {
  private final FileByPathReader fileByPathReader;

  public FileInputReader(FileByPathReader fileByPathReader) {
    this.fileByPathReader = fileByPathReader;
  }

  public <T> T entitiesFrom(String fileName, TypeReference<T> ref) {
    try {
      return new JsonFactory(new ObjectMapper()).
          createParser(fileByPathReader.fileFromPath(fileName)).
          readValueAs(ref);
    } catch (IOException e) {
      throw new InvalidInputFileException(format("Unable to read content from file: %s", fileName));
    }
  }

}
