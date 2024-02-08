package io.spring.securitysociallogin.service;

import io.spring.securitysociallogin.model.GoogleUser;
import io.spring.securitysociallogin.model.KeycloakUser;
import io.spring.securitysociallogin.model.NaverUser;
import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.model.User;
import io.spring.securitysociallogin.repository.UserRepository;
import lombok.Getter;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

  private final String REGISTRATION_ID_GOOGLE = "google";
  private final String REGISTRATION_ID_NAVER = "naver";
  private final String REGISTRATION_ID_KEYCLOAK = "keycloak";

  private final UserRepository userRepository;
  private final UserService userService;

  protected AbstractOAuth2UserService(UserRepository userRepository, UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  protected ProviderUser providerUser(ClientRegistration clientRegistration, OAuth2User oAuth2User) {
    String registrationId = clientRegistration.getRegistrationId();

    return switch (registrationId) {
      case REGISTRATION_ID_GOOGLE -> new GoogleUser(oAuth2User, clientRegistration);
      case REGISTRATION_ID_NAVER -> new NaverUser(oAuth2User, clientRegistration);
      case REGISTRATION_ID_KEYCLOAK -> new KeycloakUser(oAuth2User, clientRegistration);
      default -> null;
    };

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
