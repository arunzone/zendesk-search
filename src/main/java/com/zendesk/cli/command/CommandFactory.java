package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
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
  Map<String, Command> entityCommandRegistry = Map.ofEntries(
      entry("1", new SelectUserCommand(context, consoleDisplay)),
      entry("2", new SelectOrganizationCommand(context, consoleDisplay)),
      entry("3", new SelectTicketCommand(context, consoleDisplay)),
      entry("s", new SearchDisplayCommand(context, consoleDisplay)),
      entry("h", new HelpCommand(context, consoleDisplay))
  );
  Map<Class, SearchService> serviceRegistry = Map.ofEntries(
      entry(User.class, new SearchService(new FileRepository(new FileInputReader(new FileByPathReader()), "src/test/resources/users.json")))
  );
  FieldNameExtractor fieldNameExtractor = new FieldNameExtractor();

  public Command commandFor(String commandText) {
    if ("quit".equals(commandText)) {
      return new ExitCommand();
    }
    if (SEARCH_TERM == context.getCurrentInputType()) {
      return new SearchInputCommand(commandText, context, consoleDisplay, fieldNameExtractor);
    }
    if (SEARCH_VALUE == context.getCurrentInputType()) {
      return new SearchCommand(commandText, context, consoleDisplay, serviceRegistry.get(context.getCurrentEntity()));
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
