package io.spring.securitysociallogin.controller;

import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("/")
  public String index(Model model, Authentication authentication, @AuthenticationPrincipal
      OAuth2User oAuth2User) {

    OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
    if (null != auth2AuthenticationToken) {
      Map<String, Object> attributes = oAuth2User.getAttributes();
      String name = (String) attributes.get("name");

      if (auth2AuthenticationToken.getAuthorizedClientRegistrationId().equals("naver")) {
        Map<String, Object> response =  (Map<String, Object>)attributes.get("response");
        name = (String) response.get("name");
      }

      model.addAttribute("user", name);
    }

    return "index";
  }
}
