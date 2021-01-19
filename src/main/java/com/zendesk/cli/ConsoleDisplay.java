package com.zendesk.cli;

public class ConsoleDisplay {
  public void displayTypeSelectionTitle() {
    System.out.println("Type 'quit' to exit at any time.");
    System.out.print("Select [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket]? ");
  }

  public void displayEntityOptions() {
    System.out.println("Type 'quit' to exit at any time.");
    System.out.print("Select [1 ‣ User, 2 ‣ Organization, 3 ‣ Ticket, s ‣ Search, h ‣ Help]? ");
  }

  public void displayEntityOptions(Class clazz) {
    clearScreen();
    System.out.printf("%s is selected.\n", clazz.getSimpleName());
    displayEntityOptions();
  }

  public void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
