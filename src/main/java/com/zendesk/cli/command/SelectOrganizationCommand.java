package com.zendesk.cli.command;

import com.zendesk.entity.Organization;

public class SelectOrganizationCommand implements Command {

  private final Context context;

  public SelectOrganizationCommand(Context context) {
    this.context = context;
  }

  @Override
  public void execute() {
    context.setCurrentEntity(Organization.class);
    System.out.println("Type 'quit' to exit at any time.");
    System.out.print("Select operation [s ‣ Search, h ‣ Help]? ");
  }
}
