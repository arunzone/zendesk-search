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
