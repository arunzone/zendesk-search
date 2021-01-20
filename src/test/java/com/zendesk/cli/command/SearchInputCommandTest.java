package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.User;
import com.zendesk.search.FieldNameExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.zendesk.cli.command.InputType.SEARCH_VALUE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SearchInputCommandTest {
  @Mock
  private Context context;
  @Mock
  private ConsoleDisplay consoleDisplay;
  @Mock
  private FieldNameExtractor fieldNameExtractor;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldSetStateToSearchValueForAvailableFieldName() {
    SearchInputCommand command = new SearchInputCommand("_id", context, consoleDisplay, fieldNameExtractor);
    when(context.getCurrentEntity()).thenReturn(User.class);
    Map<Class, List<String>> fieldsMap = Map.ofEntries(Map.entry(User.class, List.of("_id")));
    when(context.getFieldNames()).thenReturn(fieldsMap);

    command.execute();

    verify(context).setCurrentInputType(SEARCH_VALUE);
  }

  @Test
  void shouldSetSearchTermValueForValidFieldName() {
    SearchInputCommand command = new SearchInputCommand("_id", context, consoleDisplay, fieldNameExtractor);
    when(context.getCurrentEntity()).thenReturn(User.class);
    Map<Class, List<String>> fieldsMap = Map.ofEntries(Map.entry(User.class, List.of("_id")));
    when(context.getFieldNames()).thenReturn(fieldsMap);

    command.execute();

    verify(context).setFieldName("_id");
  }

  @Test
  void shouldSetupFieldListAndValidateFieldName() {
    SearchInputCommand command = new SearchInputCommand("_id", context, consoleDisplay, fieldNameExtractor);
    when(context.getCurrentEntity()).thenReturn(User.class);
    Map<Class, List<String>> fieldsMap = new HashMap<>();
    when(context.getFieldNames()).thenReturn(fieldsMap);
    when(fieldNameExtractor.fieldNamesOf(User.class)).thenReturn(List.of("_id"));

    command.execute();

    List<String> userFieldNames = context.getFieldNames().get(User.class);
    assertThat(userFieldNames, contains("_id"));
  }

  @Test
  void shouldDisplaySearchValuePrompt() {
    SearchInputCommand command = new SearchInputCommand("_id", context, consoleDisplay, fieldNameExtractor);
    when(context.getCurrentEntity()).thenReturn(User.class);
    Map<Class, List<String>> fieldsMap = Map.ofEntries(Map.entry(User.class, List.of("_id")));
    when(context.getFieldNames()).thenReturn(fieldsMap);

    command.execute();

    verify(consoleDisplay).displaySearchValue("User");
  }

  @Test
  void shouldDisplaySearchTermPrompt() {
    SearchInputCommand command = new SearchInputCommand("something", context, consoleDisplay, fieldNameExtractor);
    when(context.getCurrentEntity()).thenReturn(User.class);
    Map<Class, List<String>> fieldsMap = Map.ofEntries(Map.entry(User.class, List.of("_id")));
    when(context.getFieldNames()).thenReturn(fieldsMap);

    command.execute();

    verify(consoleDisplay).displaySearchTerm("User");
  }

  @Test
  void shouldDisplayErrorMessage() throws Exception {
    SearchInputCommand command = new SearchInputCommand("something", context, consoleDisplay, fieldNameExtractor);
    when(context.getCurrentEntity()).thenReturn(User.class);
    Map<Class, List<String>> fieldsMap = Map.ofEntries(Map.entry(User.class, List.of("_id")));
    when(context.getFieldNames()).thenReturn(fieldsMap);

    String text = tapSystemOut(() -> {
      command.execute();
    });

    assertThat(text, is("Invalid search term: <something>\n"));
  }
}
