package com.zendesk.cli.command;

import com.zendesk.entity.Organization;
import org.junit.jupiter.api.Test;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class SelectOrganizationCommandTest {

  @Test
  void shouldSetCurrentContextAsOrganization() {
    Context context = new Context();
    SelectOrganizationCommand command = new SelectOrganizationCommand(context);

    command.execute();

    assertThat(context.getCurrentEntity(), is(equalTo(Organization.class)));
  }

  @Test
  void shouldPrintOrganizationOptions() throws Exception {
    Context context = new Context();
    SelectOrganizationCommand command = new SelectOrganizationCommand(context);

    command.execute();
    String text = tapSystemOut(() -> {
      command.execute();
    });

    assertThat(text, is("Type 'quit' to exit at any time.\nSelect operation [s ‣ Search, h ‣ Help]? "));
  }
}
