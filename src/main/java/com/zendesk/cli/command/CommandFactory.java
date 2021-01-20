package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.search.FieldNameExtractor;

import java.lang.reflect.Constructor;
import java.util.Map;

import static com.zendesk.cli.command.InputType.SEARCH_TERM;
import static java.util.Map.entry;

public class CommandFactory {
  private final Map<String, Class<? extends Command>> entityCommandRegistry = Map.ofEntries(
      entry("1", SelectUserCommand.class),
      entry("2", SelectOrganizationCommand.class),
      entry("3", SelectTicketCommand.class),
      entry("s", SearchDisplayCommand.class),
      entry("h", HelpCommand.class)
  );

  private final Context context = new Context();
  private final ConsoleDisplay consoleDisplay = new ConsoleDisplay();
  FieldNameExtractor fieldNameExtractor = new FieldNameExtractor();

  public Command commandFor(String commandText) {
    if ("quit".equals(commandText)) {
      return new ExitCommand();
    }
    if (SEARCH_TERM == context.getCurrentInputType()) {
      return new SearchInputCommand(commandText, context, consoleDisplay, fieldNameExtractor);
    }
    return commandFrom(commandText);
  }

  private Command commandFrom(String commandText) {
    try {
      Constructor<? extends Command> constructor = entityCommandRegistry.get(commandText).getDeclaredConstructor(Context.class, ConsoleDisplay.class);
      constructor.setAccessible(true);
      return constructor.newInstance(context, consoleDisplay);
    } catch (Exception e) {
      e.printStackTrace();
      return new InvalidCommand(commandText);
    }
  }
}
