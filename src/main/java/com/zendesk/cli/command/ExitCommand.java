package com.zendesk.cli.command;

public class ExitCommand implements Command {

  @Override
  public void execute() {
    System.exit(0);
  }
}