package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.Ticket;

public class SelectTicketCommand implements Command {

  private final Context context;
  private final ConsoleDisplay consoleDisplay;

  public SelectTicketCommand(Context context, ConsoleDisplay consoleDisplay) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
  }

  @Override
  public void execute() {
    context.setCurrentEntity(Ticket.class);
    consoleDisplay.displayEntityOptions(Ticket.class);
  }
}
