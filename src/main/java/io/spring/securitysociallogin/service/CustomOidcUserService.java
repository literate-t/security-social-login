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

//    ClientRegistration clientRegistration = userRequest.getClientRegistration();
    ClientRegistration clientRegistration = ClientRegistration.withClientRegistration(userRequest.getClientRegistration()).userNameAttributeName("sub")
            .build();

    OidcUserRequest oidcUserRequest = new OidcUserRequest(clientRegistration, userRequest.getAccessToken(), userRequest.getIdToken(), userRequest.getAdditionalParameters());

    OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService = new OidcUserService();
    // OidcUser는 OAuth2User를 상속하고 있기 때문에 OAuth2User로 해도 되지만
    // 명확하게 식별하기 위해 OidcUser를 사용한다
    OidcUser oidcUser = oidcUserService.loadUser(oidcUserRequest);

    ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration, oidcUser);
    // google or naver or keycloak
    ProviderUser providerUser = providerUser(providerUserRequest);

    // signup
    register(providerUser, userRequest);

    // 토큰이 만들어질 때 이 값이 principal로 들어간다
    return new PrincipalUser(providerUser);
  }
}
