package com.zendesk.cli;

import com.zendesk.entity.Organization;
import com.zendesk.entity.Ticket;
import com.zendesk.entity.User;
import com.zendesk.input.FileByPathReader;
import com.zendesk.input.FileInputReader;
import com.zendesk.repository.FileRepository;
import com.zendesk.search.SearchService;

import java.util.Map;

import static java.util.Map.entry;

public class SearchServiceFactory {
  private final Map<Class, SearchService> serviceRegistry;

  public SearchServiceFactory(InputFileName inputFileName) {
    FileRepository userRepository = new FileRepository(new FileInputReader(new FileByPathReader()), User.class, inputFileName.getUserFileName());
    FileRepository organizationRepository = new FileRepository(new FileInputReader(new FileByPathReader()), Organization.class, inputFileName.getOrganizationFileName());
    FileRepository ticketRepository = new FileRepository(new FileInputReader(new FileByPathReader()), Ticket.class, inputFileName.getTicketFileName());

    serviceRegistry = Map.ofEntries(
        entry(User.class, new SearchService(userRepository)),
        entry(Organization.class, new SearchService(organizationRepository)),
        entry(Ticket.class, new SearchService(ticketRepository))
    );
  }

  public SearchService serviceFor(Class entityClass) {
    return serviceRegistry.get(entityClass);
  }
}
