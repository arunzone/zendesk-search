package com.zendesk.repository;

import com.zendesk.input.FileInputReader;

import java.util.List;

public class FileRepository implements Repository {

  private final FileInputReader fileInputReader;
  private final Class entityClass;
  private String fileName;

  public FileRepository(FileInputReader fileInputReader, Class entityClass, String fileName) {
    this.fileInputReader = fileInputReader;
    this.entityClass = entityClass;
    this.fileName = fileName;
  }

  public <T> List<T> entities() {
    return (List<T>) fileInputReader.entitiesFrom(fileName, entityClass);
  }

  public void setUserInputFileName(String userInputFileName) {
    this.fileName = userInputFileName;
  }
}
