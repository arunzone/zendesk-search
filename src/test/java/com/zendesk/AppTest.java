package com.zendesk;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemErr;
import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppTest {

  private InputStream sysInBackup;

  @BeforeAll
  void setup() {
    sysInBackup = System.in;
  }

  @AfterAll
  void tearDown() {
    System.setIn(sysInBackup);
  }

  @Test
  void shouldDisplayWelcomeBanner() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("quit".getBytes());
    System.setIn(in);
    String text = tapSystemOut(() -> {
      App.main(new String[]{});

    });
    assertThat(text, is("Welcome to Zendesk search\n\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? "));
  }

  @Test
  void shouldDisplayUserSelection() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("1\nquit".getBytes());
    System.setIn(in);
    String text = tapSystemOut(() -> {
      App.main(new String[]{});

    });
    String welcomeBanner = "Welcome to Zendesk search\n\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ";
    String clearScreen = "\033[H\033[2J";
    String userSelection = welcomeBanner + clearScreen + "User is selected.\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ";
    assertThat(text, is(userSelection));
  }

  @Test
  void shouldDisplayOrganizationSelection() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("2\nquit".getBytes());
    System.setIn(in);
    String text = tapSystemOut(() -> {
      App.main(new String[]{});

    });
    String welcomeBanner = "Welcome to Zendesk search\n\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ";
    String clearScreen = "\033[H\033[2J";
    String organizationSelection = welcomeBanner + clearScreen + "Organization is selected.\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ";
    assertThat(text, is(organizationSelection));
  }

  @Test
  void shouldDisplayTicketSelection() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("3\nquit".getBytes());
    System.setIn(in);
    String text = tapSystemOut(() -> {
      App.main(new String[]{});

    });
    String welcomeBanner = "Welcome to Zendesk search\n\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ";
    String clearScreen = "\033[H\033[2J";
    String ticketSelection = welcomeBanner + clearScreen + "Ticket is selected.\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ";
    assertThat(text, is(ticketSelection));
  }

  @Test
  void shouldDisplayTicketFieldNames() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("3\nh\nquit".getBytes());
    System.setIn(in);
    String text = tapSystemOut(() -> {
      App.main(new String[]{});

    });
    String welcomeBanner = "Welcome to Zendesk search\n\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ";
    String clearScreen = "\033[H\033[2J";
    String entitySelectionOption = "Type 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ";
    String ticketSelection = welcomeBanner + clearScreen + "Ticket is selected.\n" + entitySelectionOption;
    String fieldNames = ticketSelection + clearScreen + "Search Tickets with\n-------------------------\n_id\nassignee_id\ncreated_at\ndescription\ndue_at\nexternal_id\nhas_incidents\norganization_id\npriority\nstatus\nsubject\nsubmitter_id\ntags\ntype\nurl\nvia\n-------------------------\n" + entitySelectionOption;
    assertThat(text, is(fieldNames));
  }

  @Test
  void shouldDisplayOrganizationFieldNames() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("2\nh\nquit".getBytes());
    System.setIn(in);
    String text = tapSystemOut(() -> {
      App.main(new String[]{});
    });
    String welcomeBanner = "Welcome to Zendesk search\n\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ";
    String clearScreen = "\033[H\033[2J";
    String entitySelectionOption = "Type 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ";
    String organizationSelection = welcomeBanner + clearScreen + "Organization is selected.\n" + entitySelectionOption;
    String fieldNames = organizationSelection + clearScreen + "Search Organizations with\n-------------------------\n_id\ncreated_at\ndetails\ndomain_names\nexternal_id\nname\nshared_tickets\ntags\nurl\n-------------------------\n" + entitySelectionOption;
    assertThat(text, is(fieldNames));
  }

  @Test
  void shouldDisplayUserFieldNames() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("1\nh\nquit".getBytes());
    System.setIn(in);
    String text = tapSystemOut(() -> {
      App.main(new String[]{});
    });
    String welcomeBanner = "Welcome to Zendesk search\n\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ";
    String clearScreen = "\033[H\033[2J";
    String entitySelectionOption = "Type 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ";
    String userSelection = welcomeBanner + clearScreen + "User is selected.\n" + entitySelectionOption;
    String fieldNames = userSelection + clearScreen + "Search Users with\n-------------------------\n_id\nactive\nalias\ncreated_at\nemail\nexternal_id\nlast_login_at\nlocale\nname\norganization_id\nphone\nrole\nshared\nsignature\nsuspended\ntags\ntimezone\nurl\nverified\n-------------------------\n" + entitySelectionOption;
    assertThat(text, is(fieldNames));
  }

  @Test
  void shouldDisplayUserSearchTerm() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("1\ns\nquit".getBytes());
    System.setIn(in);
    String text = tapSystemOut(() -> {
      App.main(new String[]{});
    });
    String welcomeBanner = "Welcome to Zendesk search\n\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ";
    String clearScreen = "\033[H\033[2J";
    String entitySelectionOption = "Type 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ";
    String userSelection = welcomeBanner + clearScreen + "User is selected.\n" + entitySelectionOption;
    String searchTerm = userSelection + "Enter User search term: ";
    assertThat(text, is(searchTerm));
  }

  @Test
  void shouldDisplayUserSearchValue() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("1\ns\n_id\nquit".getBytes());
    System.setIn(in);
    String text = tapSystemOut(() -> {
      App.main(new String[]{});
    });
    String welcomeBanner = "Welcome to Zendesk search\n\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ";
    String clearScreen = "\033[H\033[2J";
    String entitySelectionOption = "Type 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ";
    String userSelection = welcomeBanner + clearScreen + "User is selected.\n" + entitySelectionOption;
    String searchTerm = userSelection + "Enter User search term: ";
    String searchValue = searchTerm + "Enter User search value: ";
    assertThat(text, is(searchValue));
  }

  @Test
  void shouldDisplayUserSearchResult() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("1\ns\n_id\n4\nquit".getBytes());
    System.setIn(in);
    String text = tapSystemOut(() -> {
      App.main(new String[]{});
    });

    assertThat(text, containsString("_id                  4\n"));
  }

  @Test
  void shouldDisplayInvalidCommand() throws Exception {
    ByteArrayInputStream in = new ByteArrayInputStream("x\nquit".getBytes());
    System.setIn(in);
    String text = tapSystemErr(() -> {
      App.main(new String[]{});
    });

    String welcomeBanner = "Welcome to Zendesk search\n\nType 'quit' to exit at any time.\nSelect [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ";

    assertThat(text, containsString("Invalid command <x>\n"));
  }

}
