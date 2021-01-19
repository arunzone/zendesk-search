package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

class HelpCommandTest {

  @Test
  void shouldDisplayUserFields() throws Exception {
    Context context = new Context();
    ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
    context.setCurrentEntity(User.class);
    HelpCommand helpCommand = new HelpCommand(context, consoleDisplay);

    String text = tapSystemOut(() -> {
      helpCommand.execute();
    });
    String userFields = List.of("_id",
        "url",
        "external_id",
        "name",
        "alias",
        "created_at",
        "active",
        "verified",
        "shared",
        "locale",
        "timezone",
        "last_login_at",
        "email",
        "phone",
        "signature",
        "organization_id",
        "tags",
        "suspended",
        "role")
        .stream()
        .collect(Collectors.joining("\n"));
    assertThat(text, is(String.format("Search Users with\n------------------\n%s\n------------------\n", userFields)));

  }
}
