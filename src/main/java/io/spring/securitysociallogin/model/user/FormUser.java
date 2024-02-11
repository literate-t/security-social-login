package io.spring.securitysociallogin.model.user;

import io.spring.securitysociallogin.model.ProviderUser;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Data
@Builder
public class FormUser implements ProviderUser {

  private String id;
  private String password;
  private String username;
  private String email;
  private String provider;
  private List<? extends GrantedAuthority> authorities;

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public String getProvider() {
    return provider;
  }

  @Override
  public String getProfileImage() {
    return null;
  }

  @Override
  public List<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public OAuth2User getOAuth2User() {
    return null;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return null;
  }
}
