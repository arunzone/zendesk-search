package com.zendesk.cli.command;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.User;
import com.zendesk.search.SearchService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.zendesk.cli.command.InputType.NONE;
import static java.lang.String.format;
import static java.util.Arrays.stream;

public class SearchCommand implements Command {
  private final Context context;
  private final ConsoleDisplay consoleDisplay;
  private final String value;
  private final SearchService searchService;

  public SearchCommand(String value, Context context, ConsoleDisplay consoleDisplay, SearchService searchService) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
    this.value = value;
    this.searchService = searchService;
  }

  @Override
  public void execute() {
    context.setCurrentInputType(NONE);
    List<User> matchingEntities = searchService.findEntitiesBy(context.getFieldName(), value);
    matchingEntities.forEach(entity -> {
      Set<String> formattedFields = formattedFieldNameValues(entity);
      formattedFields.forEach(System.out::println);
      consoleDisplay.displayDivider();
    });
    consoleDisplay.displayEntityOptions(context.getCurrentEntity());
  }

  private Set<String> formattedFieldNameValues(User entity) {
    Set<String> formattedFields = stream(entity.getClass().getDeclaredFields()).map(field ->
    {
      field.setAccessible(true);
      String fieldName = fieldNameFrom(field);
      try {
        return format("%-20s %s", fieldName, field.get(entity).toString());
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      }
      return "";
    }).collect(Collectors.toSet());
    return formattedFields;
  }

  private String fieldNameFrom(java.lang.reflect.Field field) {
    String fieldName = field.getName();
    if (field.isAnnotationPresent(JsonAlias.class)) {
      String[] annotationValues = field.getAnnotation(JsonAlias.class).value();
      Optional<String> firstValue = stream(annotationValues).findFirst();
      if (firstValue.isPresent()) {
        fieldName = firstValue.get();
      }
    }
    return fieldName;
  }
}
