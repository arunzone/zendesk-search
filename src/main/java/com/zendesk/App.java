package com.zendesk;

import com.zendesk.cli.ArgumentReader;
import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.cli.SearchServiceFactory;
import com.zendesk.cli.command.CommandFactory;
import com.zendesk.search.exception.InvalidFieldException;
import com.zendesk.search.exception.InvalidFieldNameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
  public static void main(String[] args) {
    CommandFactory commandFactory = initializeFactory(args);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    try {
      process(bufferedReader, commandFactory);
    } catch (Exception exception) {
      System.err.println(exception.getMessage());
    }
  }

  private static void process(BufferedReader bufferedReader, CommandFactory commandFactory) throws IOException {
    while (true) {
      String command = bufferedReader.readLine();
      try {
        commandFactory.commandFor(command).execute();
      } catch (InvalidFieldException | InvalidFieldNameException exception) {
        System.err.println(exception.getMessage());
      }
    }
  }

  private static CommandFactory initializeFactory(String[] args) {
    ConsoleDisplay consoleDisplay = new ConsoleDisplay();
    ArgumentReader argumentReader = new ArgumentReader();
    SearchServiceFactory searchServiceFactory = new SearchServiceFactory(argumentReader.read(args));
    welcomeMessage(consoleDisplay);
    return new CommandFactory(consoleDisplay, searchServiceFactory);
  }

  private static void welcomeMessage(ConsoleDisplay consoleDisplay) {
    System.out.println("Welcome to Zendesk search\n");
    consoleDisplay.displayTypeSelectionTitle();
  }
}
