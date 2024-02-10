package io.spring.securitysociallogin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping("/api/user")
  public Authentication user(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User) {
    return authentication;
  }

  @GetMapping("/api/oidc")
  public Authentication oidc(Authentication authentication, @AuthenticationPrincipal OidcUser oidcUser) {
    return authentication;
  }
}
