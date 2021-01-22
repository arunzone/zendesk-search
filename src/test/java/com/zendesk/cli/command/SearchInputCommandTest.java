package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.zendesk.cli.command.InputType.SEARCH_VALUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchInputCommandTest {
  @Mock
  private Context context;
  @Mock
  private ConsoleDisplay consoleDisplay;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldSetStateToSearchValueForAvailableFieldName() {
    SearchInputCommand command = new SearchInputCommand("_id", context, consoleDisplay);
    when(context.getCurrentEntity()).thenReturn(User.class);

    command.execute();

    verify(context).setCurrentInputType(SEARCH_VALUE);
  }

  @Test
  void shouldSetSearchTermValueForValidFieldName() {
    SearchInputCommand command = new SearchInputCommand("_id", context, consoleDisplay);
    when(context.getCurrentEntity()).thenReturn(User.class);

    command.execute();

    verify(context).setFieldName("_id");
  }

  @Test
  void shouldDisplaySearchValuePrompt() {
    SearchInputCommand command = new SearchInputCommand("_id", context, consoleDisplay);
    when(context.getCurrentEntity()).thenReturn(User.class);

    command.execute();

    verify(consoleDisplay).displaySearchValue("User");
  }

  @Test
  void shouldDisplaySearchTermPrompt() {
    SearchInputCommand command = new SearchInputCommand("something", context, consoleDisplay);
    when(context.getCurrentEntity()).thenReturn(User.class);

    command.execute();

    verify(consoleDisplay).displaySearchTerm("User");
  }

  @Test
  void shouldDisplayErrorMessage() throws Exception {
    SearchInputCommand command = new SearchInputCommand("something", context, consoleDisplay);
    when(context.getCurrentEntity()).thenReturn(User.class);

    String text = tapSystemOut(() -> {
      command.execute();
    });

    assertThat(text, is("Invalid search term: <something>\n"));
  }
}
