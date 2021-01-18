package com.zendesk.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class Organization {
  @JsonAlias({"_id"})
  private Long id;
  private String url;
  @JsonAlias({"external_id"})
  private String externalId;
  private String name;
  @JsonAlias({"domain_names"})
  private List<String> domainNames;
  @JsonAlias({"created_at"})
  private String createdAt;
  private String details;
  @JsonAlias({"shared_tickets"})
  private Boolean sharedTickets;
  private List<String> tags;
}
