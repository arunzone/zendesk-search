package com.zendesk.cli;

public class ArgumentReader {
  public InputFileName read(String[] args) {
    InputFileName inputFileName = new InputFileName();
    for (int index = 0; index < args.length; index++) {
      if (index % 2 == 0) {
        setUserFileName(args, inputFileName, index);
        setOrganizationFileName(args, inputFileName, index);
        setTicketFileName(args, inputFileName, index);
      }
    }
    return inputFileName;
  }

  private void setUserFileName(String[] args, InputFileName inputFileName, int index) {
    if (args[index].equals("-u") && index + 1 < args.length) {
      inputFileName.setUserFileName(args[index + 1]);
    }
  }

  private void setOrganizationFileName(String[] args, InputFileName inputFileName, int index) {
    if (args[index].equals("-o") && index + 1 < args.length) {
      inputFileName.setOrganizationFileName(args[index + 1]);
    }
  }

  private void setTicketFileName(String[] args, InputFileName inputFileName, int index) {
    if (args[index].equals("-t") && index + 1 < args.length) {
      inputFileName.setTicketFileName(args[index + 1]);
    }
  }
}
