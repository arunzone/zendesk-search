package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.search.FieldNameExtractor;

import java.util.List;
import java.util.Map;

import static com.zendesk.cli.command.InputType.SEARCH_VALUE;

public class SearchInputCommand implements Command {
  private final Context context;
  private final ConsoleDisplay consoleDisplay;
  private final String fieldName;
  private FieldNameExtractor fieldNameExtractor;

  public SearchInputCommand(String fieldName, Context context, ConsoleDisplay consoleDisplay, FieldNameExtractor fieldNameExtractor) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
    this.fieldName = fieldName;
    this.fieldNameExtractor = fieldNameExtractor;
  }

  @Override
  public void execute() {
    if (valid(fieldName)) {
      context.setFieldName(fieldName);
      context.setCurrentInputType(SEARCH_VALUE);
      consoleDisplay.displaySearchValue(context.getCurrentEntity().getSimpleName());
    } else {
      System.out.printf("Invalid search term: <%s>\n", fieldName);
      consoleDisplay.displaySearchTerm(context.getCurrentEntity().getSimpleName());
    }
  }

  private boolean valid(String fieldName) {
    prepareFieldNames();
    List<String> fieldNames = context.getFieldNames().get(context.getCurrentEntity());
    return fieldNames.contains(fieldName);
  }

  private void prepareFieldNames() {
    Map<Class, List<String>> fieldNamesMap = context.getFieldNames();
    if (!fieldNamesMap.containsKey(context.getCurrentEntity())) {
      fieldNamesMap.put(context.getCurrentEntity(), getFieldNames());
    }
  }

  private List<String> getFieldNames() {
    return fieldNameExtractor.fieldNamesOf(context.getCurrentEntity());
  }
}
