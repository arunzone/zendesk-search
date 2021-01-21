package com.zendesk.input;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;

public class FileInputReader {
  private final FileByPathReader fileByPathReader;

  public FileInputReader(FileByPathReader fileByPathReader) {
    this.fileByPathReader = fileByPathReader;
  }

  public <T> List<T> entitiesFrom(String fileName, Class<T> contentClass) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      JavaType type = objectMapper.getTypeFactory().constructParametricType(List.class, contentClass);
      return objectMapper.readValue(fileByPathReader.fileFromPath(fileName), type);
    } catch (IOException e) {
      throw new InvalidInputFileException(format("Unable to read content from file: %s", fileName));
    }
  }

}
