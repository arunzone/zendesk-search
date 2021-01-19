package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.Organization;

public class SelectOrganizationCommand implements Command {

  private final Context context;
  private final ConsoleDisplay consoleDisplay;

  public SelectOrganizationCommand(Context context, ConsoleDisplay consoleDisplay) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
  }

  @Override
  public void execute() {
    context.setCurrentEntity(Organization.class);
    consoleDisplay.displayEntityOptions(Organization.class);
  }
}
