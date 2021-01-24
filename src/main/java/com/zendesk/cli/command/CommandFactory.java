package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.cli.SearchServiceFactory;
import com.zendesk.cli.report.ConsoleReportGenerator;
import com.zendesk.cli.report.FieldExtractor;
import com.zendesk.search.FieldNameExtractor;

import java.util.Map;

import static com.zendesk.cli.command.InputType.SEARCH_TERM;
import static com.zendesk.cli.command.InputType.SEARCH_VALUE;
import static java.util.Map.entry;

public class CommandFactory {
  private final Context context = new Context();
  private final ConsoleDisplay consoleDisplay = new ConsoleDisplay();
  private final Map<String, Command> entityCommandRegistry = Map.ofEntries(
      entry("1", new SelectUserCommand(context, consoleDisplay)),
      entry("2", new SelectOrganizationCommand(context, consoleDisplay)),
      entry("3", new SelectTicketCommand(context, consoleDisplay)),
      entry("s", new SearchDisplayCommand(context, consoleDisplay)),
      entry("h", new HelpCommand(context, consoleDisplay))
  );
  private final FieldNameExtractor fieldNameExtractor = new FieldNameExtractor();
  private final FieldExtractor fieldExtractor = new FieldExtractor(fieldNameExtractor);
  private final ConsoleReportGenerator consoleReportGenerator = new ConsoleReportGenerator(consoleDisplay, fieldExtractor);
  private final SearchServiceFactory searchServiceFactory = new SearchServiceFactory("src/test/resources/users.json", "src/test/resources/organizations.json", "src/test/resources/tickets.json");

  public Command commandFor(String commandText) {
    if ("quit".equals(commandText)) {
      return new ExitCommand();
    }
    if (SEARCH_TERM == context.getCurrentInputType()) {
      return new SearchInputCommand(commandText, context, consoleDisplay);
    }
    if (SEARCH_VALUE == context.getCurrentInputType()) {
      return new SearchCommand(commandText, context, consoleDisplay, searchServiceFactory.serviceFor(context.getCurrentEntity()), consoleReportGenerator);
    }
    return commandFrom(commandText);
  }

  private Command commandFrom(String commandText) {
    if (entityCommandRegistry.containsKey(commandText)) {
      return entityCommandRegistry.get(commandText);
    } else {
      return new InvalidCommand(commandText);
    }
  }
}
