package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.cli.SearchServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class CommandFactoryTest {

  @Mock
  private SearchServiceFactory searchServiceFactory;
  @Mock
  private ConsoleDisplay consoleDisplay;

  private CommandFactory commandFactory;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    commandFactory = new CommandFactory(consoleDisplay, searchServiceFactory);
  }

  @Test
  void shouldReturnExitCommandForQuitInput() {
    Command command = commandFactory.commandFor("quit");
    assertThat(command.getClass(), is(equalTo(ExitCommand.class)));
  }

  @Test
  void shouldReturnOrganizationSelectionCommand() {
    Command command = commandFactory.commandFor("2");
    assertThat(command.getClass(), is(equalTo(SelectOrganizationCommand.class)));
  }

  @Test
  void shouldReturnTicketSelectionCommand() {
    Command command = commandFactory.commandFor("3");
    assertThat(command.getClass(), is(equalTo(SelectTicketCommand.class)));
  }

  @Test
  void shouldReturnSearchSelectionCommand() {
    Command command = commandFactory.commandFor("s");
    assertThat(command.getClass(), is(equalTo(SearchDisplayCommand.class)));
  }

  @Test
  void shouldReturnHelpCommand() {
    Command command = commandFactory.commandFor("h");
    assertThat(command.getClass(), is(equalTo(HelpCommand.class)));
  }

  @Test
  void shouldReturnUserSelectCommand() {
    Command command = commandFactory.commandFor("1");
    assertThat(command.getClass(), is(equalTo(SelectUserCommand.class)));
  }

  @Test
  void shouldDisplayInvalidCommandInput() {
    Command command = commandFactory.commandFor("blah...");
    assertThat(command.getClass(), is(equalTo(InvalidCommand.class)));
  }

  @Test
  void shouldReturnSearchInputCommand() {
    commandFactory.commandFor("1").execute();
    commandFactory.commandFor("s").execute();
    Command command = commandFactory.commandFor("_id");
    assertThat(command.getClass(), is(equalTo(SearchInputCommand.class)));
  }

  @Test
  void shouldReturnSearchCommand() {
    commandFactory.commandFor("1").execute();
    commandFactory.commandFor("s").execute();
    commandFactory.commandFor("_id").execute();
    Command command = commandFactory.commandFor("2");
    assertThat(command.getClass(), is(equalTo(SearchCommand.class)));
  }
}
