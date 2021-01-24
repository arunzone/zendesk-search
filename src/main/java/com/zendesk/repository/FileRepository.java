package com.zendesk.repository;

import com.zendesk.input.FileInputReader;

import java.util.List;

public class FileRepository<T> implements Repository {

  private final FileInputReader fileInputReader;
  private final Class entityClass;
  private final String fileName;
  private List<T> entities;

  public FileRepository(FileInputReader fileInputReader, Class entityClass, String fileName) {
    this.fileInputReader = fileInputReader;
    this.entityClass = entityClass;
    this.fileName = fileName;
  }

  public List<T> entities() {
    if (entities == null) {
      entities = (List<T>) fileInputReader.entitiesFrom(fileName, entityClass);
    }
    return entities;
  }
}
