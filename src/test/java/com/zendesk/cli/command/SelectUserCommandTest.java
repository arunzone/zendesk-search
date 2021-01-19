package com.zendesk.cli.command;

import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.zendesk.cli.command.Entity.USER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SelectUserCommandTest {

  @Test
  void shouldSetCurrentContextAsUser() {
    Context context = new Context();
    SelectUserCommand command = new SelectUserCommand(context);

    command.execute();

    assertThat(context.getCurrent(), is(USER));
  }

  @Test
  void shouldPrintUserOptions() throws Exception {
    Context context = new Context();
    SelectUserCommand command = new SelectUserCommand(context);

    command.execute();
    String text = tapSystemOut(() -> {
      command.execute();
    });

    assertThat(text, is("Type 'quit' to exit at any time.\nSelect 1) Search 2) Help\n"));

    assertThat(context.getCurrent(), is(USER));
  }
}
