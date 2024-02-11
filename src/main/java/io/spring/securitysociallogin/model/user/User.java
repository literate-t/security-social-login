package io.spring.securitysociallogin.model.user;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
public class User {
  private String registrationId;
  private String id;
  private String username;
  private String password;
  private String provider;
  private String email;
  private List<? extends GrantedAuthority> authorities;
}
