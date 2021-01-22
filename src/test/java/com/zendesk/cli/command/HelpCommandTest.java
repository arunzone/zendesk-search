package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.User;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;

class HelpCommandTest {

  @Test
  void shouldDisplayUserFields() {
    Context context = new Context();
    ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
    context.setCurrentEntity(User.class);
    HelpCommand helpCommand = new HelpCommand(context, consoleDisplay);

    helpCommand.execute();

    verify(consoleDisplay).displayHelp(
        argThat(Matchers.allOf(
            hasItem(equalTo("_id")),
            hasItem(equalTo("url")),
            hasItem(equalTo("external_id")),
            hasItem(equalTo("name")),
            hasItem(equalTo("alias")),
            hasItem(equalTo("created_at")),
            hasItem(equalTo("active")),
            hasItem(equalTo("verified")),
            hasItem(equalTo("shared")),
            hasItem(equalTo("locale")),
            hasItem(equalTo("timezone")),
            hasItem(equalTo("last_login_at")),
            hasItem(equalTo("email")),
            hasItem(equalTo("phone")),
            hasItem(equalTo("signature")),
            hasItem(equalTo("organization_id")),
            hasItem(equalTo("tags")),
            hasItem(equalTo("suspended")),
            hasItem(equalTo("role")))
        ), eq("User"));
  }
}
