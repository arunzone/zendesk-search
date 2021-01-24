package com.zendesk.cli;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class ArgumentReaderTest {
  @Test
  void shouldReadDefaultUserInputFileName() {
    ArgumentReader argumentReader = new ArgumentReader();
    InputFileName inputFileName = argumentReader.read(new String[]{});
    assertThat(inputFileName.getUserFileName(), is("src/test/resources/users.json"));
  }

  @Test
  void shouldReadDefaultOrganizationInputFileName() {
    ArgumentReader argumentReader = new ArgumentReader();
    InputFileName inputFileName = argumentReader.read(new String[]{});
    assertThat(inputFileName.getOrganizationFileName(), is("src/test/resources/organizations.json"));
  }

  @Test
  void shouldReadDefaultTicketInputFileName() {
    ArgumentReader argumentReader = new ArgumentReader();

    InputFileName inputFileName = argumentReader.read(new String[]{});

    assertThat(inputFileName.getTicketFileName(), is("src/test/resources/tickets.json"));
  }

  @Test
  void shouldReadUserInputFileName() {
    ArgumentReader argumentReader = new ArgumentReader();

    InputFileName inputFileName = argumentReader.read(new String[]{"-u", "user/file/name"});

    assertThat(inputFileName.getUserFileName(), is("user/file/name"));
  }

  @Test
  void shouldReadOrganizationInputFileName() {
    ArgumentReader argumentReader = new ArgumentReader();

    InputFileName inputFileName = argumentReader.read(new String[]{"-o", "user/file/name"});

    assertThat(inputFileName.getOrganizationFileName(), is("user/file/name"));
  }

  @Test
  void shouldReadTicketInputFileName() {
    ArgumentReader argumentReader = new ArgumentReader();

    InputFileName inputFileName = argumentReader.read(new String[]{"-t", "user/file/name"});

    assertThat(inputFileName.getTicketFileName(), is("user/file/name"));
  }

}
