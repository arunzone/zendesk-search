package com.zendesk.cli.command;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zendesk.cli.command.InputType.NONE;

@Data
public class Context {
  private Class currentEntity;
  private InputType currentInputType = NONE;
  private String fieldName;
  private String fieldValue;
  private Map<Class, List<String>> fieldNames = new HashMap<>();
}
