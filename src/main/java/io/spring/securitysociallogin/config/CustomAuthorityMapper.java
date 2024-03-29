package io.spring.securitysociallogin.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

public class CustomAuthorityMapper implements GrantedAuthoritiesMapper {
  @Override
  public Set<GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
    HashSet<GrantedAuthority> mapped = new HashSet<>(authorities.size());
    for (GrantedAuthority authority : authorities) {
      mapped.add(mapAuthority(authority.getAuthority()));
    }

    return mapped;
  }

  private GrantedAuthority mapAuthority(String name) {
    if (0 < name.lastIndexOf(".")) {
      int index = name.lastIndexOf(".");
      name = "SCOPE_".concat(name.substring(index + 1));
    }

    if (!name.startsWith("ROLE_")) {
      name = "ROLE_".concat(name);
    }

    return new SimpleGrantedAuthority(name);
  }
}
