package com.zendesk.cli.command;

import com.zendesk.search.FieldNameExtractor;

import java.util.List;
import java.util.Map;

import static java.lang.String.join;

public class HelpCommand implements Command {

  private final Context context;

  public HelpCommand(Context context) {
    this.context = context;
  }

  @Override
  public void execute() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.printf("Search %ss with\n------------------%n", context.getCurrentEntity().getSimpleName());
    System.out.println(fieldNames());
    System.out.print("------------------\nEnter search term: ");
  }

  private String fieldNames() {
    Map<Class, String> fieldNamesMap = context.getFieldNames();
    if (!fieldNamesMap.containsKey(context.getCurrentEntity())) {
      fieldNamesMap.put(context.getCurrentEntity(), formattedFieldNames());
    }
    return fieldNamesMap.get(context.getCurrentEntity());
  }

  private String formattedFieldNames() {
    FieldNameExtractor fieldNameExtractor = new FieldNameExtractor();
    List<String> fieldNames = fieldNameExtractor.fieldNamesOf(context.getCurrentEntity());
    return join("\n", fieldNames);
  }
}
