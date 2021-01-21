package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.cli.report.ConsoleReportGenerator;
import com.zendesk.entity.User;
import com.zendesk.search.SearchService;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.List;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchCommandTest {

  @Test
  void shouldDisplayMatchingResult() {
    Context context = mock(Context.class);
    ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
    SearchService searchService = mock(SearchService.class);
    ConsoleReportGenerator consoleReportGenerator = mock(ConsoleReportGenerator.class);
    User user = new User();
    List<Object> matchingUsers = List.of(user);
    SearchCommand searchCommand = new SearchCommand("arun@rockit.com", context, consoleDisplay, searchService, consoleReportGenerator);

    when(context.getFieldName()).thenReturn("email");
    when(context.getCurrentEntity()).thenReturn(User.class);
    when(searchService.findEntitiesBy("email", "arun@rockit.com")).thenReturn(matchingUsers);

    searchCommand.execute();

    verify(consoleReportGenerator).report(matchingUsers);
  }

  @Test
  void shouldDisplayOptionsAfterResultDisplay() {
    Context context = mock(Context.class);
    ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
    SearchService searchService = mock(SearchService.class);
    ConsoleReportGenerator consoleReportGenerator = mock(ConsoleReportGenerator.class);
    User user = new User();
    List<Object> matchingUsers = List.of(user);
    SearchCommand searchCommand = new SearchCommand("arun@rockit.com", context, consoleDisplay, searchService, consoleReportGenerator);

    when(context.getFieldName()).thenReturn("email");
    when(context.getCurrentEntity()).thenReturn(User.class);
    when(searchService.findEntitiesBy("email", "arun@rockit.com")).thenReturn(matchingUsers);

    searchCommand.execute();

    InOrder inOrder = inOrder(consoleReportGenerator, consoleDisplay);
    inOrder.verify(consoleReportGenerator).report(matchingUsers);
    inOrder.verify(consoleDisplay).displayEntityOptions(User.class);
  }
}
