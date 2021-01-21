package com.zendesk.cli.report;

import com.zendesk.cli.ConsoleDisplay;
import com.zendesk.entity.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConsoleReportGeneratorTest {
  @Test
  void shouldDisplayMatchingResult() throws Exception {
    ConsoleDisplay consoleDisplay = mock(ConsoleDisplay.class);
    FieldExtractor fieldExtractor = mock(FieldExtractor.class);
    ConsoleReportGenerator consoleReportGenerator = new ConsoleReportGenerator(consoleDisplay, fieldExtractor);

    User user = new User();
    ReportField reportField = new ReportField("timezone", "Netherlands");
    when(fieldExtractor.fieldsFor(user)).thenReturn(Set.of(reportField));

    String text = tapSystemOut(() -> {
      consoleReportGenerator.report(List.of(user));
    });

    assertThat(text, is("timezone             Netherlands\n"));
  }
}
