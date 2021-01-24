package com.zendesk.cli.command;

public class InvalidCommand implements Command {

  private final String commandText;

  public InvalidCommand(String commandText) {
    this.commandText = commandText;
  }

  @Override
  public void execute() {
    System.err.println(String.format("Invalid command <%s>", commandText));
  }
}
