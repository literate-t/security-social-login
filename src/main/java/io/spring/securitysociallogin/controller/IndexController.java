package io.spring.securitysociallogin.controller;

import io.spring.securitysociallogin.model.PrincipalUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("/")
  public String index(Model model, @AuthenticationPrincipal
  PrincipalUser principalUser) {

    if (null != principalUser) {
      // OAuth2나 Oidc나 providerUser가 세팅이 되어 있어서 분기 안 해도 된다
//      String username = null;
//      if (authentication instanceof OAuth2AuthenticationToken) {
//        username = OAuth2Utils.oAuth2Username((OAuth2AuthenticationToken) authentication,
//            principalUser);
//      } else {
//        username = principalUser.providerUser().getUsername();
//      }
      String username = principalUser.providerUser().getUsername();

      model.addAttribute("user", username);
      model.addAttribute("provider", principalUser.providerUser().getProvider());
    }

    return "index";
  }
}
