package com.zendesk.input;


import com.zendesk.entity.Organization;
import com.zendesk.entity.Ticket;
import com.zendesk.entity.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FileInputReaderTest {

  @Test
  void shouldReturnFirstUser() {
    FileByPathReader fileByPathReader = mock(FileByPathReader.class);
    FileInputReader reader = new FileInputReader(fileByPathReader);
    User user = new User();
    user.setId(1L);
    user.setUrl("http://initech.zendesk.com/api/v2/users/1.json");
    user.setExternalId("74341f74-9c79-49d5-9611-87ef9b6eb75f");
    user.setName("Francisca Rasmussen");
    user.setAlias("Miss Coffey");
    user.setCreatedAt("2016-04-15T05:19:46 -10:00");
    user.setActive(true);
    user.setVerified(true);
    user.setShared(false);
    user.setLocale("en-AU");
    user.setTimezone("Sri Lanka");
    user.setLastLoginAt("2013-08-04T01:03:27 -10:00");
    user.setEmail("coffeyrasmussen@flotonic.com");
    user.setPhone("8335-422-718");
    user.setSignature("Don't Worry Be Happy!");
    user.setOrganizationId(119L);
    user.setTags(List.of("Springville", "Sutton", "Hartsville/Hartley", "Diaperville"));
    user.setSuspended(true);
    user.setRole("admin");

    File userFile = new File("src/test/resources/users.json");
    when(fileByPathReader.fileFromPath("src/test/resources/users.json")).thenReturn(userFile);

    List<User> users = reader.entitiesFrom("src/test/resources/users.json", User.class);

    assertThat(users.stream().findFirst().get(), is(user));
  }

  @Test
  void shouldMatchRetrievedUsersCount() {
    FileByPathReader fileByPathReader = mock(FileByPathReader.class);
    FileInputReader reader = new FileInputReader(fileByPathReader);

    File userFile = new File("src/test/resources/users.json");
    when(fileByPathReader.fileFromPath("src/test/resources/users.json")).thenReturn(userFile);

    List<User> users = reader.entitiesFrom("src/test/resources/users.json", User.class);

    assertThat(users.size(), is(75));
  }

  @Test
  void shouldThrowExceptionForMismatchingInvalidInput() {
    FileByPathReader fileByPathReader = mock(FileByPathReader.class);
    FileInputReader reader = new FileInputReader(fileByPathReader);

    File userFile = new File(getClass().getClassLoader().getResource("tickets.json").getFile());
    when(fileByPathReader.fileFromPath("src/test/resources/users.json")).thenReturn(userFile);

    InvalidInputFileException invalidInputFileException = assertThrows(InvalidInputFileException.class, () -> reader.entitiesFrom("src/test/resources/users.json", User.class));

    assertThat(invalidInputFileException.getMessage(), is("Unable to read content from file: src/test/resources/users.json"));
  }

  @Test
  void shouldReturnFirstTicket() {
    FileByPathReader fileByPathReader = mock(FileByPathReader.class);
    FileInputReader reader = new FileInputReader(fileByPathReader);
    Ticket ticket = new Ticket();
    ticket.setId("436bf9b0-1147-4c0a-8439-6f79833bff5b");
    ticket.setUrl("http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json");
    ticket.setExternalId("9210cdc9-4bee-485f-a078-35396cd74063");
    ticket.setCreatedAt("2016-04-28T11:19:34 -10:00");
    ticket.setType("incident");
    ticket.setSubject("A Catastrophe in Korea (North)");
    ticket.setDescription("Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum.");
    ticket.setPriority("high");
    ticket.setStatus("pending");
    ticket.setSubmitterId(38L);
    ticket.setAssigneeId(24L);
    ticket.setOrganizationId(116L);
    ticket.setTags(List.of("Ohio", "Pennsylvania", "American Samoa", "Northern Mariana Islands"));
    ticket.setHasIncidents(false);
    ticket.setDueAt("2016-07-31T02:37:50 -10:00");
    ticket.setVia("web");

    File userFile = new File("src/test/resources/tickets.json");
    when(fileByPathReader.fileFromPath("src/test/resources/tickets.json")).thenReturn(userFile);

    List<Ticket> users = reader.entitiesFrom("src/test/resources/tickets.json", Ticket.class);

    assertThat(users.stream().findFirst().get(), is(ticket));
  }

  @Test
  void shouldReturnFirstOrganization() {
    FileByPathReader fileByPathReader = mock(FileByPathReader.class);
    FileInputReader reader = new FileInputReader(fileByPathReader);
    Organization organization = new Organization();
    organization.setId(101L);
    organization.setUrl("http://initech.zendesk.com/api/v2/organizations/101.json");
    organization.setExternalId("9270ed79-35eb-4a38-a46f-35725197ea8d");
    organization.setName("Enthaze");
    organization.setDomainNames(List.of("kage.com", "ecratic.com", "endipin.com", "zentix.com"));
    organization.setCreatedAt("2016-05-21T11:10:28 -10:00");
    organization.setDetails("MegaCorp");
    organization.setSharedTickets(false);
    organization.setTags(List.of("Fulton", "West", "Rodriguez", "Farley"));

    File userFile = new File("src/test/resources/organizations.json");
    when(fileByPathReader.fileFromPath("src/test/resources/organizations.json")).thenReturn(userFile);

    List<Organization> users = reader.entitiesFrom("src/test/resources/organizations.json", Organization.class);

    assertThat(users.stream().findFirst().get(), is(organization));
  }
}
