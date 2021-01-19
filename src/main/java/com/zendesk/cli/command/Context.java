package com.zendesk.cli.command;

import lombok.Data;

enum Entity {USER}

@Data
public class Context {
  private Entity current;
}
