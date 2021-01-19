package com.zendesk.cli.command;

import com.zendesk.entity.Ticket;

public class SelectTicketCommand implements Command {

  private final Context context;

  public SelectTicketCommand(Context context) {
    this.context = context;
  }

  @Override
  public void execute() {
    context.setCurrentEntity(Ticket.class);
    System.out.println("Type 'quit' to exit at any time.");
    System.out.print("Select operation [s ‣ Search, h ‣ Help]? ");
  }
}
