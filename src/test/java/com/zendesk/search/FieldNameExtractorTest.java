package com.zendesk.search;

import com.zendesk.entity.Organization;
import com.zendesk.entity.Ticket;
import com.zendesk.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

class FieldNameExtractorTest {

  private FieldNameExtractor fieldNameExtractor = new FieldNameExtractor();

  @Test
  void shouldReturnAllUserFields() {
    List<String> fieldNames = fieldNameExtractor.fieldNamesOf(User.class);

    assertThat(fieldNames, contains(
        "_id",
        "url",
        "external_id",
        "name",
        "alias",
        "created_at",
        "active",
        "verified",
        "shared",
        "locale",
        "timezone",
        "last_login_at",
        "email",
        "phone",
        "signature",
        "organization_id",
        "tags",
        "suspended",
        "role"));
  }

  @Test
  void shouldReturnAllTicketFields() {
    List<String> fieldNames = fieldNameExtractor.fieldNamesOf(Ticket.class);

    assertThat(fieldNames, contains(
        "_id",
        "url",
        "external_id",
        "created_at",
        "type",
        "subject",
        "description",
        "priority",
        "status",
        "submitter_id",
        "assignee_id",
        "organization_id",
        "tags",
        "has_incidents",
        "due_at",
        "via"));
  }

  @Test
  void shouldReturnAllOrganizationFields() {
    List<String> fieldNames = fieldNameExtractor.fieldNamesOf(Organization.class);

    assertThat(fieldNames, contains(
        "_id",
        "url",
        "external_id",
        "name",
        "domain_names",
        "created_at",
        "details",
        "shared_tickets",
        "tags"));
  }
}
