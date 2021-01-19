package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.User;


public class SelectUserCommand implements Command {

  private final Context context;
  private final ConsoleDisplay consoleDisplay;

  public SelectUserCommand(Context context, ConsoleDisplay consoleDisplay) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
  }

  @Override
  public void execute() {
    context.setCurrentEntity(User.class);
    consoleDisplay.displayEntityOptions(User.class);
  }
}
