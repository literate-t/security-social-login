package io.spring.securitysociallogin.repository;

import io.spring.securitysociallogin.model.User;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private final Map<String, Object> users = new HashMap<>();

  public User findByUsername(String username) {
    if (users.containsKey(username)) {
      return (User) users.get(username);
    }

    return null;
  }

  public void register(User user) {
    if (!users.containsKey(user.getUsername())) {
      users.put(user.getUsername(), user);
    }
  }
}
