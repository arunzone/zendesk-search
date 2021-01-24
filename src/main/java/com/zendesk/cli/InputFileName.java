package com.zendesk.cli;

import lombok.Data;

@Data
public class InputFileName {
  private String userFileName = "src/test/resources/users.json";
  private String organizationFileName = "src/test/resources/organizations.json";
  private String ticketFileName = "src/test/resources/tickets.json";
}
