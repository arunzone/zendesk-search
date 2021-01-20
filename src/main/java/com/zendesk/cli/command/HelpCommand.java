package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.search.FieldNameExtractor;

import java.util.List;
import java.util.Map;

import static java.lang.String.join;

public class HelpCommand implements Command {

  private final Context context;
  private final ConsoleDisplay consoleDisplay;

  public HelpCommand(Context context, ConsoleDisplay consoleDisplay) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
  }

  @Override
  public void execute() {
    consoleDisplay.clearScreen();
    System.out.printf("Search %ss with\n------------------\n", context.getCurrentEntity().getSimpleName());
    System.out.println(fieldNames());
    System.out.println("------------------");
    consoleDisplay.displayEntityOptions();
  }

  private String fieldNames() {
    Map<Class, List<String>> fieldNamesMap = context.getFieldNames();
    if (!fieldNamesMap.containsKey(context.getCurrentEntity())) {
      fieldNamesMap.put(context.getCurrentEntity(), getFieldNames());
    }
    List<String> fieldNames = fieldNamesMap.get(context.getCurrentEntity());
    return formatted(fieldNames);
  }

  private String formatted(List<String> fieldNames) {
    return join("\n", fieldNames);
  }

  private List<String> getFieldNames() {
    FieldNameExtractor fieldNameExtractor = new FieldNameExtractor();
    List<String> fieldNames = fieldNameExtractor.fieldNamesOf(context.getCurrentEntity());
    return fieldNames;
  }
}
