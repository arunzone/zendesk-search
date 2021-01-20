package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.User;
import org.junit.jupiter.api.Test;

import static com.zendesk.cli.command.InputType.SEARCH_TERM;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchDisplayCommandTest {

  @Test
  void shouldSetCurrentSearchInputStateAsSearchTerm() {
    Context context = mock(Context.class);
    ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
    SearchDisplayCommand command = new SearchDisplayCommand(context, consoleDisplay);
    when(context.getCurrentEntity()).thenReturn(User.class);

    command.execute();

    verify(context).setCurrentInputType(SEARCH_TERM);
  }

  @Test
  void shouldDisplaySearchTerm() {
    Context context = mock(Context.class);
    ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
    SearchDisplayCommand command = new SearchDisplayCommand(context, consoleDisplay);
    when(context.getCurrentEntity()).thenReturn(User.class);

    command.execute();

    verify(consoleDisplay).displaySearchTerm("User");
  }
}
