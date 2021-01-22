package com.zendesk.search;

public class InvalidFieldNameException extends RuntimeException {
  public InvalidFieldNameException(String message) {
    super(message);
  }
}
