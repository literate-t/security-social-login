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
  public OAuth2User user(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2User) {
    System.out.println("authentication = " + authentication + ", oAuth2User = "+ oAuth2User);
    return oAuth2User;
  }

  @GetMapping("/api/oidc")
  public OidcUser oidc(Authentication authentication, @AuthenticationPrincipal OidcUser oidcUser) {
    System.out.println("authentication = " + authentication + ", oidcUser = "+ oidcUser);
    return oidcUser;
  }
}
