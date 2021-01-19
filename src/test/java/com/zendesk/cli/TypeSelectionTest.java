package com.zendesk.cli;

import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class TypeSelectionTest {

  @Test
  void givenTapSystemOut_whenInvokePrintln_thenOutputIsReturnedSuccessfully() throws Exception {

    String text = tapSystemOut(() -> {
      new TypeSelection().displayTypeSelectionTitle();
    });

    assertThat(text, is("Type 'quit' to exit at any time.\nSelect [1 ‣ User 2 ‣ Organization 3 ‣ Ticket]? "));
  }
}
