package com.zendesk.search;

public class InvalidFieldException extends RuntimeException {
  public InvalidFieldException(String message) {
    super(message);
  }
}
