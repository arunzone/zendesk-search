package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.search.FieldNameAssociationFactory;

import java.util.Set;

import static com.zendesk.cli.command.InputType.SEARCH_VALUE;

public class SearchInputCommand implements Command {
  private final Context context;
  private final ConsoleDisplay consoleDisplay;
  private final String fieldName;

  public SearchInputCommand(String fieldName, Context context, ConsoleDisplay consoleDisplay) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
    this.fieldName = fieldName;
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
    Set<String> fieldNames = FieldNameAssociationFactory.fieldNameMapFor(context.getCurrentEntity()).keySet();
    return fieldNames.contains(fieldName);
  }
}
