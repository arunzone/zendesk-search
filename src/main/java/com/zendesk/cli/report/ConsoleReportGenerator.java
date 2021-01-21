package com.zendesk.cli.report;

import com.zendesk.cli.ConsoleDisplay;

import java.util.List;
import java.util.Set;

import static java.lang.String.format;

public class ConsoleReportGenerator {
  private final ConsoleDisplay consoleDisplay;
  private final FieldExtractor fieldExtractor;

  public ConsoleReportGenerator(ConsoleDisplay consoleDisplay, FieldExtractor fieldExtractor) {
    this.consoleDisplay = consoleDisplay;
    this.fieldExtractor = fieldExtractor;
  }

  public <T> void report(List<T> result) {
    result.forEach(entity -> {
      Set<ReportField> reportFields = fieldExtractor.fieldsFor(entity);
      reportFields.stream()
          .map(field -> format("%-20s %s", field.getName(), field.getValue()))
          .forEach(System.out::println);
      consoleDisplay.displayDivider();
    });
  }
}
