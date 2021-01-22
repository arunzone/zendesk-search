package com.zendesk;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.cli.command.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
  public static void main(String[] args) {
    CommandFactory commandFactory = initializeFactory();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      while (true) {
        String command = br.readLine();
        commandFactory.commandFor(command).execute();
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private static CommandFactory initializeFactory() {
    ConsoleDisplay consoleDisplay = new ConsoleDisplay();
    welcomeMessage(consoleDisplay);
    CommandFactory commandFactory = new CommandFactory();
    return commandFactory;
  }

  private static void welcomeMessage(ConsoleDisplay consoleDisplay) {
    System.out.println("Welcome to Zendesk search\n");
    consoleDisplay.displayTypeSelectionTitle();
  }
}
