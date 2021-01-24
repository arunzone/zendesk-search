package com.zendesk.cli;

import com.zendesk.entity.Organization;
import com.zendesk.entity.Ticket;
import com.zendesk.entity.User;
import com.zendesk.search.SearchService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

class SearchServiceFactoryTest {
  private final SearchServiceFactory searchServiceFactory = new SearchServiceFactory(mock(InputFileName.class));

  @Test
  void shouldReturnSearchServiceForUser() {
    SearchService searchService = searchServiceFactory.serviceFor(User.class);
    MatcherAssert.assertThat(searchService.getClass(), is(IsEqual.equalTo(SearchService.class)));
  }

  @Test
  void shouldReturnSearchServiceForOrganization() {
    SearchService searchService = searchServiceFactory.serviceFor(Organization.class);
    MatcherAssert.assertThat(searchService.getClass(), is(IsEqual.equalTo(SearchService.class)));
  }

  @Test
  void shouldReturnSearchServiceForTicket() {
    SearchService searchService = searchServiceFactory.serviceFor(Ticket.class);
    MatcherAssert.assertThat(searchService.getClass(), is(IsEqual.equalTo(SearchService.class)));
  }

}
