package io.spring.securitysociallogin.util;

import io.spring.securitysociallogin.model.Attributes;
import java.util.Map;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class OAuth2Utils {
  public static Attributes getMainAttributes(OAuth2User oAuth2User) {
    return Attributes.builder()
        .mainAttributes(oAuth2User.getAttributes()).build();
  }

  public static Attributes getSubAttributes(OAuth2User oAuth2User, String subAttributeKey) {
    Map<String, Object> subAttributes = (Map<String, Object>) oAuth2User.getAttributes()
        .get(subAttributeKey);

    return Attributes.builder()
        .subAttributes(subAttributes).build();
  }

  public  static Attributes getOtherAttributes(OAuth2User oAuth2User, String subAttributeKey, String otherAttributeKey) {
    Map<String, Object> subAttributes = (Map<String, Object>) oAuth2User.getAttributes()
        .get(subAttributeKey);
    Map<String, Object> otherAttributes = (Map<String, Object>) oAuth2User.getAttributes()
        .get(otherAttributeKey);

    return Attributes.builder()
        .subAttributes(subAttributes)
        .otherAttributes(otherAttributes).build();
  }
}
