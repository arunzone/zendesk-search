package com.zendesk.repository;

import com.zendesk.entity.User;
import com.zendesk.input.FileInputReader;

import java.util.List;

public class FileRepository implements Repository {

  private final FileInputReader fileInputReader;
  private String userInputFileName = "src/test/resources/users.json";

  public FileRepository(FileInputReader fileInputReader) {
    this.fileInputReader = fileInputReader;
  }

  @Override
  public List<User> users() {
    return fileInputReader.usersFrom(userInputFileName);
  }

  public void setUserInputFileName(String userInputFileName) {
    this.userInputFileName = userInputFileName;
  }
}
