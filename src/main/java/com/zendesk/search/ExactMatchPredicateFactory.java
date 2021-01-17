package com.zendesk.search;

import com.zendesk.entity.User;

import java.lang.reflect.Field;
import java.util.List;

public class ExactMatchPredicateFactory<T> {

  public boolean match(User user, String fieldName, String value) {
    try {
      Field field = user.getClass().getDeclaredField(fieldName);
      field.setAccessible(true);
      if (field.getType().getName().equals(List.class.getName())) {
        return ((List) field.get(user)).contains(value);
      }

      String fieldValue = String.valueOf(field.get(user));
      return fieldValue.equals(value);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return false;
  }
}
