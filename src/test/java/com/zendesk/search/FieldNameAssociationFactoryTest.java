package com.zendesk.search;

import com.zendesk.entity.Organization;
import com.zendesk.entity.Ticket;
import com.zendesk.entity.User;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class FieldNameAssociationFactoryTest {
  @Test
  void shouldHaveFieldsMappedForUser() {
    FieldNameAssociationFactory factory = new FieldNameAssociationFactory();

    Map<String, String> userFieldNameMap = factory.fieldNameMapFor(User.class);

    assertThat(userFieldNameMap.size(), is(19));
  }

  @Test
  void shouldHaveFieldsMappedForOrganization() {
    FieldNameAssociationFactory factory = new FieldNameAssociationFactory();

    Map<String, String> organizationFieldNameMap = factory.fieldNameMapFor(Organization.class);

    assertThat(organizationFieldNameMap.size(), is(9));
  }

  @Test
  void shouldHaveFieldsMappedForTicket() {
    FieldNameAssociationFactory factory = new FieldNameAssociationFactory();

    Map<String, String> ticketFieldNameMap = factory.fieldNameMapFor(Ticket.class);

    assertThat(ticketFieldNameMap.size(), is(16));
  }

}
