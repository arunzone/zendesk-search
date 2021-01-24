package com.zendesk.cli;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.join;

public class ConsoleDisplay {
  public void displayTypeSelectionTitle() {
    System.out.println("Type 'quit' to exit at any time.");
    System.out.print("Select [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ");
  }

  public void displayEntityOptions() {
    System.out.println("Type 'quit' to exit at any time.");
    System.out.print("Select [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ");
  }

  public void displaySearchTerm(String entityName) {
    System.out.printf("Enter %s search term: ", entityName);
  }

  public void displaySearchValue(String entityName) {
    System.out.printf("Enter %s search value: ", entityName);
  }

  public void displayDivider() {
    System.out.println("-------------------------");
  }

  public void displayEntityOptions(Class clazz) {
    clearScreen();
    displayEntityOptionsWithoutClear(clazz);
  }

  public void displayEntityOptionsWithoutClear(Class clazz) {
    System.out.printf("%s is selected.\n", clazz.getSimpleName());
    displayEntityOptions();
  }

  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public void displayHelp(Set<String> fieldNames, String entitySelection) {
    clearScreen();
    System.out.printf("Search %ss with\n", entitySelection);
    displayDivider();
    System.out.println(join("\n", fieldNames.stream().sorted().collect(Collectors.toList())));
    displayDivider();
    displayEntityOptions();
  }
}
