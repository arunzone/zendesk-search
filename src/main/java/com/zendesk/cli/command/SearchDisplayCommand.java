package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;

public class SearchDisplayCommand implements Command {
  private final Context context;
  private final ConsoleDisplay consoleDisplay;

  public SearchDisplayCommand(Context context, ConsoleDisplay consoleDisplay) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
  }

  @Override
  public void execute() {
    consoleDisplay.displaySearchTerm(context.getCurrentEntity().getSimpleName());
  }
}
