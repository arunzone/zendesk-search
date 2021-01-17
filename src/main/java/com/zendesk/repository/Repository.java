package com.zendesk.repository;

import com.zendesk.entity.User;

import java.util.List;

public interface Repository {
  List<User> users();
}
