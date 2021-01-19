package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;

import java.lang.reflect.Constructor;
import java.util.Map;

import static java.util.Map.entry;

public class CommandFactory {
  private final Map<String, Class<? extends Command>> entityCommandRegistry = Map.ofEntries(
      entry("1", SelectUserCommand.class),
      entry("2", SelectOrganizationCommand.class),
      entry("3", SelectTicketCommand.class),
      entry("h", HelpCommand.class)
  );
  private Context context = new Context();
  private ConsoleDisplay consoleDisplay = new ConsoleDisplay();

  public Command commandFor(String commandText) {
    if ("quit".equals(commandText)) {
      return new ExitCommand();
    }
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
