package com.zendesk.cli.command;

import com.zendesk.entity.Ticket;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SelectTicketCommandTest {
  @Test
  void shouldSetCurrentContextAsTicket() {
    Context context = new Context();
    SelectTicketCommand command = new SelectTicketCommand(context);

    command.execute();

    assertThat(context.getCurrentEntity(), is(equalTo(Ticket.class)));
  }

  @Test
  void shouldPrintOrganizationOptions() throws Exception {
    Context context = new Context();
    SelectTicketCommand command = new SelectTicketCommand(context);

    command.execute();
    String text = tapSystemOut(() -> {
      command.execute();
    });

    assertThat(text, is("Type 'quit' to exit at any time.\nSelect operation [s ‣ Search, h ‣ Help]? "));
  }
}
