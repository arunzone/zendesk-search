package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

class SelectOrganizationCommandTest {
  @Mock
  private ConsoleDisplay consoleDisplay;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldSetCurrentContextAsOrganization() {
    Context context = new Context();
    SelectOrganizationCommand command = new SelectOrganizationCommand(context, consoleDisplay);

    command.execute();

    assertThat(context.getCurrentEntity(), is(equalTo(Organization.class)));
  }

  @Test
  void shouldPrintOrganizationOptions() {
    Context context = new Context();
    SelectOrganizationCommand command = new SelectOrganizationCommand(context, consoleDisplay);

    command.execute();

    verify(consoleDisplay).displayEntityOptions(Organization.class);
  }
}
