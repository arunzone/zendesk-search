package com.zendesk.search;

import com.zendesk.entity.Organization;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Map.entry;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

class FieldNameMapperTest {
  @Test
  void shouldReturnAllAnnotatedAttributesToActualOnes() {
    FieldNameMapper fieldNameMapper = new FieldNameMapper(new FieldNameExtractor());
    Map<String, String> mappedFieldNames = fieldNameMapper.mappedFieldNamesFor(Organization.class);

    MatcherAssert.assertThat(mappedFieldNames.entrySet(), containsInAnyOrder(
        entry("_id", "id"),
        entry("url", "url"),
        entry("name", "name"),
        entry("domain_names", "domainNames"),
        entry("created_at", "createdAt"),
        entry("details", "details"),
        entry("shared_tickets", "sharedTickets"),
        entry("tags", "tags"),
        entry("external_id", "externalId")));
  }
}
