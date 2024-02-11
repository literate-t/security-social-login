package io.spring.securitysociallogin.service;

import io.spring.securitysociallogin.converter.ProviderUserConverter;
import io.spring.securitysociallogin.converter.ProviderUserRequest;
import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.model.user.User;
import io.spring.securitysociallogin.repository.UserRepository;
import lombok.Getter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

  private final String REGISTRATION_ID_GOOGLE = "google";
  private final String REGISTRATION_ID_NAVER = "naver";
  private final String REGISTRATION_ID_KEYCLOAK = "keycloak";

  private final UserRepository userRepository;
  private final UserService userService;
  private final ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

  protected AbstractOAuth2UserService(UserRepository userRepository, UserService userService,
      ProviderUserConverter providerUserConverter) {
    this.userRepository = userRepository;
    this.userService = userService;
    this.providerUserConverter = providerUserConverter;
  }

  protected ProviderUser providerUser(ProviderUserRequest providerUserRequest) {
    return providerUserConverter.convert(providerUserRequest);
  }

  protected void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {
    User user = userRepository.findByUsername(providerUser.getUsername());
    if (null == user) {
      String registrationId = userRequest.getClientRegistration().getRegistrationId();
      userService.register(registrationId, providerUser);
    } else {
      System.out.println("Registered user = " + user);
    }
  }
}
