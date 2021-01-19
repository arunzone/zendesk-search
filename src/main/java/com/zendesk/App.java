package com.zendesk;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.cli.command.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
  public static void main(String[] args) {

    ConsoleDisplay consoleDisplay = new ConsoleDisplay();
    CommandFactory commandFactory = new CommandFactory();
    System.out.println("Welcome to Zendesk search\n");
    consoleDisplay.displayTypeSelectionTitle();
    boolean proceed = true;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    try {
      while (proceed) {
        String command = br.readLine();
        commandFactory.commandFor(command).execute();
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
