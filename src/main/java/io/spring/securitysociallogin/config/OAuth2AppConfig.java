package io.spring.securitysociallogin.config;

import io.spring.securitysociallogin.common.authority.CustomAuthorityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OAuth2AppConfig {
  @Bean
  public CustomAuthorityMapper customAuthorityMapper() {
    return new CustomAuthorityMapper();
  }
}
