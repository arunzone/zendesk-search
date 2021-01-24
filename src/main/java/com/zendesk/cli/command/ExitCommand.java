package com.zendesk.cli.command;

import com.zendesk.cli.command.exception.TerminateException;

public class ExitCommand implements Command {

  @Override
  public void execute() {
    throw new TerminateException("Bye");
  }
}
