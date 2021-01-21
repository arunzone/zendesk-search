package com.zendesk.cli.report;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportField {
  private String name;
  private String value;
}
