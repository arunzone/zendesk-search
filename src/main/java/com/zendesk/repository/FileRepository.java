package com.zendesk.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zendesk.input.FileInputReader;

import java.util.List;

public class FileRepository<T> implements Repository<T> {

  private final FileInputReader fileInputReader;
  private String fileName;

  public FileRepository(FileInputReader fileInputReader, String fileName) {
    this.fileInputReader = fileInputReader;
    this.fileName = fileName;
  }

  @Override
  public List<T> entities() {
    return fileInputReader.entitiesFrom(fileName, new TypeReference<>() {
    });
  }

  public void setUserInputFileName(String userInputFileName) {
    this.fileName = userInputFileName;
  }
}
