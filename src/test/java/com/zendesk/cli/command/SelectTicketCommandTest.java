package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.Ticket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

class SelectTicketCommandTest {
  @Mock
  private ConsoleDisplay consoleDisplay;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldSetCurrentContextAsTicket() {
    Context context = new Context();
    SelectTicketCommand command = new SelectTicketCommand(context, consoleDisplay);

    command.execute();

    assertThat(context.getCurrentEntity(), is(equalTo(Ticket.class)));
  }

  @Test
  void shouldPrintOrganizationOptions() {
    Context context = new Context();
    SelectTicketCommand command = new SelectTicketCommand(context, consoleDisplay);

    command.execute();

    verify(consoleDisplay).displayEntityOptions(Ticket.class);
  }
}
