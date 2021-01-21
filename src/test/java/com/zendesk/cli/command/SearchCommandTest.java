package com.zendesk.cli.command;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.User;
import com.zendesk.search.SearchService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchCommandTest {

  @Test
  void shouldDisplayMatchingResult() throws Exception {
    Context context = mock(Context.class);
    ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
    SearchService searchService = mock(SearchService.class);

    when(context.getFieldName()).thenReturn("email");
    User user = new User();
    user.setId(4L);
    user.setUrl("http://initech.zendesk.com/api/v2/users/4.json");
    user.setExternalId("37c9aef5-cf01-4b07-af24-c6c49ac1d1c7");
    user.setName("Rose Newton");
    user.setAlias("Mr Cardenas");
    user.setCreatedAt("2016-02-09T07:52:10 -11:00");
    user.setActive(true);
    user.setVerified(true);
    user.setShared(false);
    user.setLocale("de-CH");
    user.setTimezone("Netherlands");
    user.setLastLoginAt("2012-09-25T01:32:46 -10:00");
    user.setEmail("cardenasnewton@flotonic.com");
    user.setPhone("8685-482-450");
    user.setSignature("Don't Worry Be Happy!");
    user.setOrganizationId(122L);
    user.setTags(List.of("Gallina", "Glenshaw", "Rowe", "Babb"));
    user.setSuspended(true);
    user.setRole("end-user");

    List<Object> matchingUsers = List.of(user);
    when(searchService.findEntitiesBy("email", "arun@rockit.com")).thenReturn(matchingUsers);
    SearchCommand searchCommand = new SearchCommand("arun@rockit.com", context, consoleDisplay, searchService);

    searchCommand.execute();

    String text = tapSystemOut(() -> {
      searchCommand.execute();
    });

    assertThat(text, is("url                  http://initech.zendesk.com/api/v2/users/4.json\n" +
        "created_at           2016-02-09T07:52:10 -11:00\n" +
        "timezone             Netherlands\n" +
        "email                cardenasnewton@flotonic.com\n" +
        "locale               de-CH\n" +
        "shared               false\n" +
        "alias                Mr Cardenas\n" +
        "phone                8685-482-450\n" +
        "external_id          37c9aef5-cf01-4b07-af24-c6c49ac1d1c7\n" +
        "_id                  4\n" +
        "role                 end-user\n" +
        "last_login_at        2012-09-25T01:32:46 -10:00\n" +
        "tags                 [Gallina, Glenshaw, Rowe, Babb]\n" +
        "active               true\n" +
        "signature            Don't Worry Be Happy!\n" +
        "suspended            true\n" +
        "name                 Rose Newton\n" +
        "verified             true\n" +
        "organization_id      122\n"));
  }
}
