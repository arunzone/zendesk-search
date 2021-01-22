package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.cli.report.ConsoleReportGenerator;
import com.zendesk.cli.report.FieldExtractor;
import com.zendesk.entity.Organization;
import com.zendesk.entity.Ticket;
import com.zendesk.entity.User;
import com.zendesk.input.FileByPathReader;
import com.zendesk.input.FileInputReader;
import com.zendesk.repository.FileRepository;
import com.zendesk.search.FieldNameExtractor;
import com.zendesk.search.SearchService;

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
  private final Map<Class, SearchService> serviceRegistry = Map.ofEntries(
      entry(User.class, new SearchService(new FileRepository(new FileInputReader(new FileByPathReader()), User.class, "src/test/resources/users.json"))),
      entry(Organization.class, new SearchService(new FileRepository(new FileInputReader(new FileByPathReader()), Organization.class, "src/test/resources/organizations.json"))),
      entry(Ticket.class, new SearchService(new FileRepository(new FileInputReader(new FileByPathReader()), Ticket.class, "src/test/resources/tickets.json")))
  );

  public Command commandFor(String commandText) {
    if ("quit".equals(commandText)) {
      return new ExitCommand();
    }
    if (SEARCH_TERM == context.getCurrentInputType()) {
      return new SearchInputCommand(commandText, context, consoleDisplay);
    }
    if (SEARCH_VALUE == context.getCurrentInputType()) {
      return new SearchCommand(commandText, context, consoleDisplay, serviceRegistry.get(context.getCurrentEntity()), consoleReportGenerator);
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
