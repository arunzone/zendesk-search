package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.cli.SearchServiceFactory;
import com.zendesk.cli.report.ConsoleReportGenerator;
import com.zendesk.cli.report.FieldExtractor;
import com.zendesk.search.FieldNameExtractor;
import com.zendesk.search.SearchService;

import java.util.Map;

import static com.zendesk.cli.command.InputType.SEARCH_TERM;
import static com.zendesk.cli.command.InputType.SEARCH_VALUE;
import static java.util.Map.entry;

public class CommandFactory {
  private final Context context = new Context();
  private final ConsoleDisplay consoleDisplay;
  private final Map<String, Command> entityCommandRegistry;
  private final ConsoleReportGenerator consoleReportGenerator;
  private final SearchServiceFactory searchServiceFactory;

  public CommandFactory(ConsoleDisplay consoleDisplay, SearchServiceFactory searchServiceFactory) {
    this.consoleDisplay = consoleDisplay;
    entityCommandRegistry = Map.ofEntries(
        entry("1", new SelectUserCommand(context, consoleDisplay)),
        entry("2", new SelectOrganizationCommand(context, consoleDisplay)),
        entry("3", new SelectTicketCommand(context, consoleDisplay)),
        entry("s", new SearchDisplayCommand(context, consoleDisplay)),
        entry("h", new HelpCommand(context, consoleDisplay))
    );
    FieldNameExtractor fieldNameExtractor = new FieldNameExtractor();
    FieldExtractor fieldExtractor = new FieldExtractor(fieldNameExtractor);
    consoleReportGenerator = new ConsoleReportGenerator(consoleDisplay, fieldExtractor);
    this.searchServiceFactory = searchServiceFactory;
  }

  public Command commandFor(String commandText) {
    if ("quit".equals(commandText)) {
      return new ExitCommand();
    }
    if (SEARCH_TERM == context.getCurrentInputType()) {
      return new SearchInputCommand(commandText, context, consoleDisplay);
    }
    if (SEARCH_VALUE == context.getCurrentInputType()) {
      SearchService searchService = searchServiceFactory.serviceFor(context.getCurrentEntity());
      return new SearchCommand(commandText, context, consoleDisplay, searchService, consoleReportGenerator);
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
