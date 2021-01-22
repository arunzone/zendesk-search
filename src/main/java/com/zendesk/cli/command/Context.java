package com.zendesk.cli.command;

import lombok.Data;

import static com.zendesk.cli.command.InputType.NONE;

@Data
public class Context {
  private Class currentEntity;
  private InputType currentInputType = NONE;
  private String fieldName;
}
