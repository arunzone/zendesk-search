package com.zendesk.cli.command;

import com.zendesk.entity.User;


public class SelectUserCommand implements Command {

  private final Context context;

  public SelectUserCommand(Context context) {
    this.context = context;
  }

  @Override
  public void execute() {
    context.setCurrent(User.class);
    System.out.println("Type 'quit' to exit at any time.");
    System.out.print("Select operation [s) Search, h) Help]? ");
  }
}
