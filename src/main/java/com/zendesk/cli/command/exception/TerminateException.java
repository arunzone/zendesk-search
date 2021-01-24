package com.zendesk.cli.command.exception;

public class TerminateException extends RuntimeException {
  public TerminateException(String message) {
    super(message);
  }
}
