package com.zendesk.search.exception;

public class InvalidFieldException extends RuntimeException {
  public InvalidFieldException(String message) {
    super(message);
  }
}
