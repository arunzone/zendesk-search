package com.zendesk.repository;

import com.zendesk.entity.User;
import com.zendesk.input.FileInputReader;

import java.util.List;

public class FileRepository implements Repository {

  private final FileInputReader fileInputReader;
  private String fileName;

  public FileRepository(FileInputReader fileInputReader, String fileName) {
    this.fileInputReader = fileInputReader;
    this.fileName = fileName;
  }

  public <T> List<T> entities() {
    return (List<T>) fileInputReader.entitiesFrom(fileName, User.class);
  }

  public void setUserInputFileName(String userInputFileName) {
    this.fileName = userInputFileName;
  }
}
