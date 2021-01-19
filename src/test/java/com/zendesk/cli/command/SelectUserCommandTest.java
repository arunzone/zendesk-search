package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

class SelectUserCommandTest {
  @Mock
  private ConsoleDisplay consoleDisplay;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldSetCurrentContextAsUser() {
    Context context = new Context();
    SelectUserCommand command = new SelectUserCommand(context, consoleDisplay);

    command.execute();

    assertThat(context.getCurrentEntity(), is(equalTo(User.class)));
  }

  @Test
  void shouldPrintUserOptions() {
    Context context = new Context();
    SelectUserCommand command = new SelectUserCommand(context, consoleDisplay);

    command.execute();

    verify(consoleDisplay).displayEntityOptions(User.class);
  }
}
