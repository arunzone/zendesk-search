package com.zendesk.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.List;

@Data
public class User {
  @JsonAlias({"_id"})
  private Long id;
  private String url;
  @JsonAlias({"external_id"})
  private String externalId;
  private String name;
  private String alias;
  @JsonAlias({"created_at"})
  private String createdAt;
  private Boolean active;
  private Boolean verified;
  private Boolean shared;
  private String locale;
  private String timezone;
  @JsonAlias({"last_login_at"})
  private String lastLoginAt;
  private String email;
  private String phone;
  private String signature;
  @JsonAlias({"organization_id"})
  private Long organizationId;
  private List<String> tags;
  private Boolean suspended;
  private String role;
}
