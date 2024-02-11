package io.spring.securitysociallogin.service;

import io.spring.securitysociallogin.common.converter.ProviderUserConverter;
import io.spring.securitysociallogin.common.converter.ProviderUserRequest;
import io.spring.securitysociallogin.model.PrincipalUser;
import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.repository.UserRepository;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class CustomOidcUserService extends AbstractOAuth2UserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

  protected CustomOidcUserService(
      UserRepository userRepository,
      UserService userService,
      ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter) {
    super(userRepository, userService, providerUserConverter);
  }

  @Override
  public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
    ClientRegistration clientRegistration = userRequest.getClientRegistration();
    OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService = new OidcUserService();
    OidcUser oidcUser = oidcUserService.loadUser(userRequest);

    ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration, oidcUser);
    // google or naver or keycloak
    ProviderUser providerUser = providerUser(providerUserRequest);

    // signup
    register(providerUser, userRequest);

    return new PrincipalUser(providerUser);
  }
}
