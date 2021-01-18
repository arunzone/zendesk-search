package com.zendesk.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class Ticket {
  @JsonAlias({"_id"})
  private String id;
  private String url;
  @JsonAlias({"external_id"})
  private String externalId;
  @JsonAlias({"created_at"})
  private String createdAt;
  private String type;
  private String subject;
  private String description;
  private String priority;
  private String status;
  @JsonAlias({"submitter_id"})
  private Long submitterId;
  @JsonAlias({"assignee_id"})
  private Long assigneeId;
  @JsonAlias({"organization_id"})
  private Long organizationId;
  private List<String> tags;
  @JsonAlias({"has_incidents"})
  private Boolean hasIncidents;
  @JsonAlias({"due_at"})
  private String dueAt;
  private String via;
}
