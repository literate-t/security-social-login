package io.spring.securitysociallogin.model.social;

import io.spring.securitysociallogin.model.Attributes;
import io.spring.securitysociallogin.model.OAuth2ProviderUser;
import java.util.Map;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class KakaoUser extends OAuth2ProviderUser {

  Map<String, Object> mainAttributes;

  public KakaoUser(Attributes attributes, OAuth2User oAuth2User,
      ClientRegistration clientRegistration) {
    super(attributes.getMainAttributes(), oAuth2User, clientRegistration);
    this.mainAttributes = attributes.getMainAttributes();
  }

  @Override
  public String getId() {
    return (String) getAttributes().get("id");
  }

  @Override
  public String getUsername() {
    return (String) mainAttributes.get("nickname");
  }

  @Override
  public String getProfileImage() {
    return (String) mainAttributes.get("picture");
  }
}
