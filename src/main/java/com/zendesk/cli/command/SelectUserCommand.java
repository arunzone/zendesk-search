package com.zendesk.cli.command;

import static com.zendesk.cli.command.Entity.USER;

public class SelectUserCommand implements Command {

  private final Context context;

  public SelectUserCommand(Context context) {
    this.context = context;
  }

  @Override
  public void execute() {
    context.setCurrent(USER);
    System.out.println("Type 'quit' to exit at any time.");
    System.out.println("Select 1) Search 2) Help");
  }
}
