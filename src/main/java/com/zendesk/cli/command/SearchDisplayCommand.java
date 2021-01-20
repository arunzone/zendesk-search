package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;

import static com.zendesk.cli.command.InputType.SEARCH_TERM;

public class SearchDisplayCommand implements Command {
  private final Context context;
  private final ConsoleDisplay consoleDisplay;

  public SearchDisplayCommand(Context context, ConsoleDisplay consoleDisplay) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
  }

  @Override
  public void execute() {
    context.setCurrentInputType(SEARCH_TERM);
    consoleDisplay.displaySearchTerm(context.getCurrentEntity().getSimpleName());
  }
}
