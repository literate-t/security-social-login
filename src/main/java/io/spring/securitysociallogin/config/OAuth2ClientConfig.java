package io.spring.securitysociallogin.config;

import io.spring.securitysociallogin.service.CustomOAuth2UserService;
import io.spring.securitysociallogin.service.CustomOidcUserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class OAuth2ClientConfig {

  private final CustomOAuth2UserService customOAuth2UserService;
  private final CustomOidcUserService customOidcUserService;

  public OAuth2ClientConfig(CustomOAuth2UserService customOAuth2UserService,
      CustomOidcUserService customOidcUserService) {
    this.customOAuth2UserService = customOAuth2UserService;
    this.customOidcUserService = customOidcUserService;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();

    http.authorizeRequests(
        expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry.antMatchers("/")
            .permitAll()
            .antMatchers("/api/users").access("hasAnyRole('SCOPE_profile', 'SCOPE_email')")
            .antMatchers("/api/oidc").access("hasAnyRole('SCOPE_openid')")
            .anyRequest().authenticated());

    http.oauth2Login(Customizer.withDefaults());
    http.oauth2Login(httpSecurityOAuth2LoginConfigurer ->
        httpSecurityOAuth2LoginConfigurer.userInfoEndpoint(userInfoEndpointConfig ->
            userInfoEndpointConfig.userService(customOAuth2UserService).oidcUserService(customOidcUserService)));
    http.logout().logoutSuccessUrl("/");

    return http.build();
  }

  @Bean
  public GrantedAuthoritiesMapper customAuthorityMapper() {
    return new CustomAuthorityMapper();
  }
}
