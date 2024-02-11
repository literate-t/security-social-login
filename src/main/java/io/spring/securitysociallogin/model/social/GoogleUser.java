package io.spring.securitysociallogin.model.social;

import io.spring.securitysociallogin.model.Attributes;
import io.spring.securitysociallogin.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class GoogleUser extends OAuth2ProviderUser {

  public GoogleUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
    super(attributes.getMainAttributes(), oAuth2User, clientRegistration);
  }

  @Override
  public String getId() {
    return (String) getAttributes().get("sub");
  }

  @Override
  public String getUsername() {
    return (String) getAttributes().get("sub");
  }
}
