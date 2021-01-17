package com.zendesk.input;

import java.io.File;

import static java.lang.String.format;

public class FileByPathReader {
  public File fileFromPath(String absolutePath) {
    File file = new File(absolutePath);
    if (!file.exists() || !file.isFile()) {
      throw new InvalidInputFileException(format("Invalid file location: %s", absolutePath));
    }
    return file;
  }
}
