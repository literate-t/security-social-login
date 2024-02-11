package io.spring.securitysociallogin.service;

import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.model.user.User;
import io.spring.securitysociallogin.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  protected UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void register(String registrationId, ProviderUser providerUser) {
    User user = User.builder()
        .id(providerUser.getId())
        .registrationId(registrationId)
        .username(providerUser.getUsername())
        .password(providerUser.getPassword())
        .provider(providerUser.getProvider())
        .email(providerUser.getEmail())
        .authorities(providerUser.getAuthorities())
        .build();

    userRepository.register(user);
  }
}
