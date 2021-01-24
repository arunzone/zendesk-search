package com.zendesk.cli.report;

import com.zendesk.entity.User;
import com.zendesk.search.FieldNameExtractor;
import com.zendesk.search.exception.InvalidFieldException;
import lombok.SneakyThrows;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FieldExtractorTest {

  @Test
  void shouldExtractFieldNameAndValueWhenAvailable() {
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

    FieldNameExtractor fieldNameExtractor = new FieldNameExtractor();
    FieldExtractor fieldExtractor = new FieldExtractor(fieldNameExtractor);

    Set<ReportField> fields = fieldExtractor.fieldsFor(user);

    MatcherAssert.assertThat(fields, containsInAnyOrder(
        new ReportField("created_at", "2016-02-09T07:52:10 -11:00"),
        new ReportField("active", "true"),
        new ReportField("verified", "true"),
        new ReportField("email", "cardenasnewton@flotonic.com"),
        new ReportField("url", "http://initech.zendesk.com/api/v2/users/4.json"),
        new ReportField("external_id", "37c9aef5-cf01-4b07-af24-c6c49ac1d1c7"),
        new ReportField("name", "Rose Newton"),
        new ReportField("shared", "false"),
        new ReportField("locale", "de-CH"),
        new ReportField("timezone", "Netherlands"),
        new ReportField("last_login_at", "2012-09-25T01:32:46 -10:00"),
        new ReportField("phone", "8685-482-450"),
        new ReportField("signature", "Don't Worry Be Happy!"),
        new ReportField("organization_id", "122"),
        new ReportField("tags", "[Gallina, Glenshaw, Rowe, Babb]"),
        new ReportField("suspended", "true"),
        new ReportField("_id", "4"),
        new ReportField("alias", "Mr Cardenas"),
        new ReportField("role", "end-user")));
  }

  @Test
  void shouldExtractFieldLocaleEmptyWhenNotAvailable() {
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
    user.setTimezone("Netherlands");
    user.setLastLoginAt("2012-09-25T01:32:46 -10:00");
    user.setEmail("cardenasnewton@flotonic.com");
    user.setPhone("8685-482-450");
    user.setSignature("Don't Worry Be Happy!");
    user.setOrganizationId(122L);
    user.setTags(List.of("Gallina", "Glenshaw", "Rowe", "Babb"));
    user.setSuspended(true);
    user.setRole("end-user");

    FieldNameExtractor fieldNameExtractor = new FieldNameExtractor();
    FieldExtractor fieldExtractor = new FieldExtractor(fieldNameExtractor);

    Set<ReportField> fields = fieldExtractor.fieldsFor(user);

    MatcherAssert.assertThat(fields, containsInAnyOrder(
        new ReportField("created_at", "2016-02-09T07:52:10 -11:00"),
        new ReportField("active", "true"),
        new ReportField("verified", "true"),
        new ReportField("email", "cardenasnewton@flotonic.com"),
        new ReportField("url", "http://initech.zendesk.com/api/v2/users/4.json"),
        new ReportField("external_id", "37c9aef5-cf01-4b07-af24-c6c49ac1d1c7"),
        new ReportField("name", "Rose Newton"),
        new ReportField("shared", "false"),
        new ReportField("locale", ""),
        new ReportField("timezone", "Netherlands"),
        new ReportField("last_login_at", "2012-09-25T01:32:46 -10:00"),
        new ReportField("phone", "8685-482-450"),
        new ReportField("signature", "Don't Worry Be Happy!"),
        new ReportField("organization_id", "122"),
        new ReportField("tags", "[Gallina, Glenshaw, Rowe, Babb]"),
        new ReportField("suspended", "true"),
        new ReportField("_id", "4"),
        new ReportField("alias", "Mr Cardenas"),
        new ReportField("role", "end-user")));
  }

  @SneakyThrows
  @Test
  void shouldReturnNullIfFieldHasAccessRestriction() {
    User user = new User();
    FieldExtractor fieldExtractor = new FieldExtractor(null);

    InvalidFieldException invalidFieldException = assertThrows(InvalidFieldException.class, () -> fieldExtractor.fieldValueFrom(user, user.getClass().getDeclaredField("id")));

    assertThat(invalidFieldException.getMessage(), is("Problem accessing field value"));
  }
}
