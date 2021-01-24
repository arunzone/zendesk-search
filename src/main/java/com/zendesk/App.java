package com.zendesk;

import com.zendesk.cli.ArgumentReader;
import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.cli.SearchServiceFactory;
import com.zendesk.cli.command.CommandFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
  public static void main(String[] args) {
    CommandFactory commandFactory = initializeFactory(args);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      while (true) {
        String command = br.readLine();
        commandFactory.commandFor(command).execute();
      }
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
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
