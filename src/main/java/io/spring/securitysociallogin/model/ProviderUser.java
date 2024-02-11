package io.spring.securitysociallogin.model;

import java.util.List;
import java.util.Map;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface ProviderUser {

  String getId();
  String getUsername();
  String getPassword();
  String getEmail();

  String getProvider();

  String getProfileImage();

  List<? extends GrantedAuthority> getAuthorities();

  OAuth2User getOAuth2User();

  Map<String, Object> getAttributes();
}
