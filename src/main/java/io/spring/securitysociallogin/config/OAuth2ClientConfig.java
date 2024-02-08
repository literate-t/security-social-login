package io.spring.securitysociallogin.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class OAuth2ClientConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests().requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();

    http.authorizeRequests(
        expressionInterceptUrlRegistry -> expressionInterceptUrlRegistry.antMatchers("/")
            .permitAll()
            .anyRequest().authenticated());

    http.oauth2Login(Customizer.withDefaults());
    http.logout().logoutSuccessUrl("/");

    return http.build();
  }
}
