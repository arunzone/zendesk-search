package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.search.FieldNameAssociationFactory;

import java.util.Set;

public class HelpCommand implements Command {

  private final Context context;
  private final ConsoleDisplay consoleDisplay;

  public HelpCommand(Context context, ConsoleDisplay consoleDisplay) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
  }

  @Override
  public void execute() {
    Set<String> fieldNames = FieldNameAssociationFactory.fieldNameMapFor(context.getCurrentEntity()).keySet();
    consoleDisplay.displayHelp(fieldNames, context.getCurrentEntity().getSimpleName());
  }

}
