package com.zendesk.cli.command;

import com.zendesk.cli.command.exception.TerminateException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExitCommandTest {

  @Test
  private void shouldThrowTerminateException() {
    TerminateException terminateException = assertThrows(TerminateException.class, () -> new ExitCommand().execute());

    assertThat(terminateException.getMessage(), is("Bye"));
  }
}
