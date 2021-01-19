package com.zendesk.cli;

import com.zendesk.entity.User;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ConsoleDisplayTest {

  @Test
  void shouldDisplayEntityTypeSelection() throws Exception {
    String text = tapSystemOut(() -> {
      new ConsoleDisplay().displayTypeSelectionTitle();
    });

    assertThat(text, is("Type 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ")
    );
  }

  @Test
  void shouldDisplayEntityOption() throws Exception {
    String text = tapSystemOut(() -> {
      new ConsoleDisplay().displayEntityOptions();
    });

    assertThat(text, is("Type 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ")
    );
  }

  @Test
  void shouldDisplayEntityOptionWithCurrentEntitySelection() throws Exception {
    String text = tapSystemOut(() -> {
      new ConsoleDisplay().displayEntityOptions(User.class);
    });

    assertThat(text, is("\033[H\033[2JUser is selected.\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ")
    );
  }

  @Test
  void shouldClearScreen() throws Exception {
    String text = tapSystemOut(() -> {
      new ConsoleDisplay().clearScreen();
    });

    assertThat(text, is("\033[H\033[2J")
    );
  }

  @Test
  void shouldDisplaySearchTerm() throws Exception {
    String text = tapSystemOut(() -> {
      new ConsoleDisplay().displaySearchTerm("User");
    });

    assertThat(text, is("Enter User search term: ")
    );
  }
}
