package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.cli.report.ConsoleReportGenerator;
import com.zendesk.search.SearchService;

import java.util.List;

import static com.zendesk.cli.command.InputType.NONE;

public class SearchCommand implements Command {
  private final Context context;
  private final ConsoleDisplay consoleDisplay;
  private final String value;
  private final SearchService searchService;
  private final ConsoleReportGenerator consoleReportGenerator;

  public SearchCommand(String value, Context context, ConsoleDisplay consoleDisplay, SearchService searchService, ConsoleReportGenerator consoleReportGenerator) {
    this.context = context;
    this.consoleDisplay = consoleDisplay;
    this.value = value;
    this.searchService = searchService;
    this.consoleReportGenerator = consoleReportGenerator;
  }

  @Override
  public void execute() {
    context.setCurrentInputType(NONE);
    List<Object> matchingEntities = searchService.findEntitiesBy(context.getFieldName(), value);
    consoleReportGenerator.report(matchingEntities);
    consoleDisplay.displayEntityOptionsWithoutClear(context.getCurrentEntity());
  }

}
