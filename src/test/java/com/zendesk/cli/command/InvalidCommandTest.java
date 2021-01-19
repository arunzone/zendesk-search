package com.zendesk.cli.command;

import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class InvalidCommandTest {
  @Test
  void shouldPrintInvalidCommandTextMessage() throws Exception {
    String text = tapSystemOut(() -> {
      new InvalidCommand("blah...").execute();
    });

    assertThat(text, is("Invalid command <blah...>\n"));

  }
}
