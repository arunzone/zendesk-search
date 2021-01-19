package com.zendesk.cli.command;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class CommandFactoryTest {
  @Test
  void shouldReturnExitCommandForQuitInput() {
    Command command = new CommandFactory().commandFor("quit");
    assertThat(command.getClass(), is(equalTo(ExitCommand.class)));
  }

  @Test
  void shouldReturnOrganizationSelectionCommand() {
    Command command = new CommandFactory().commandFor("2");
    assertThat(command.getClass(), is(equalTo(SelectOrganizationCommand.class)));
  }

  @Test
  void shouldReturnTicketSelectionCommand() {
    Command command = new CommandFactory().commandFor("3");
    assertThat(command.getClass(), is(equalTo(SelectTicketCommand.class)));
  }

  @Test
  void shouldReturnSearchSelectionCommand() {
    Command command = new CommandFactory().commandFor("s");
    assertThat(command.getClass(), is(equalTo(SearchDisplayCommand.class)));
  }

  @Test
  void shouldReturnHelpCommand() {
    Command command = new CommandFactory().commandFor("h");
    assertThat(command.getClass(), is(equalTo(HelpCommand.class)));
  }

  @Test
  void shouldReturnUserSelectCommand() {
    Command command = new CommandFactory().commandFor("1");
    assertThat(command.getClass(), is(equalTo(SelectUserCommand.class)));
  }

  @Test
  void shouldDisplayInvalidCommandInput() {
    Command command = new CommandFactory().commandFor("blah...");
    assertThat(command.getClass(), is(equalTo(InvalidCommand.class)));
  }
}
