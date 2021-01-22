package com.zendesk.search;

import com.zendesk.entity.Organization;
import com.zendesk.entity.Ticket;
import com.zendesk.entity.User;

import java.util.Map;

import static java.util.Map.entry;

public final class FieldNameAssociationFactory {

  private static final Map<Class, Map<String, String>> fieldNamesRegistry;

  static {
    FieldNameMapper fieldNameMapper = new FieldNameMapper(new FieldNameExtractor());
    fieldNamesRegistry = Map.ofEntries(
        entry(User.class, fieldNameMapper.mappedFieldNamesFor(User.class)),
        entry(Organization.class, fieldNameMapper.mappedFieldNamesFor(Organization.class)),
        entry(Ticket.class, fieldNameMapper.mappedFieldNamesFor(Ticket.class))
    );
  }

  public static Map<String, String> fieldNameMapFor(Class entityClass) {
    return fieldNamesRegistry.get(entityClass);
  }

}
