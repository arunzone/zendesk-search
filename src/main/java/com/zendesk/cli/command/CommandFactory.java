package com.zendesk.cli.command;

import java.lang.reflect.Constructor;
import java.util.Map;

import static java.util.Map.entry;

public class CommandFactory {
  private final Map<String, Class<? extends Command>> entityCommandRegistry = Map.ofEntries(
      entry("1", SelectUserCommand.class)
  );
  private Context context = new Context();

  public Command commandFor(String commandText) {
    if ("quit".equals(commandText)) {
      return new ExitCommand();
    }
    try {
      Constructor<? extends Command> constructor = entityCommandRegistry.get(commandText).getDeclaredConstructor(Context.class);
      constructor.setAccessible(true);
      return constructor.newInstance(context);
    } catch (Exception e) {
      e.printStackTrace();
      return new InvalidCommand(commandText);
    }
  }
}
