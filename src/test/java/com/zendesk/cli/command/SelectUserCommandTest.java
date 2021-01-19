package com.zendesk.cli.command;

import com.zendesk.entity.User;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SelectUserCommandTest {

  @Test
  void shouldSetCurrentContextAsUser() {
    Context context = new Context();
    SelectUserCommand command = new SelectUserCommand(context);

    command.execute();

    assertThat(context.getCurrentEntity(), is(equalTo(User.class)));
  }

  @Test
  void shouldPrintUserOptions() throws Exception {
    Context context = new Context();
    SelectUserCommand command = new SelectUserCommand(context);

    command.execute();
    String text = tapSystemOut(() -> {
      command.execute();
    });

    assertThat(text, is("Type 'quit' to exit at any time.\nSelect operation [s ‣ Search, h ‣ Help]? "));
  }
}
