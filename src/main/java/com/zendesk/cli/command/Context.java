package com.zendesk.cli.command;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Context {
  private Class current;
  private Map<Class, String> fieldNames = new HashMap<>();
}
