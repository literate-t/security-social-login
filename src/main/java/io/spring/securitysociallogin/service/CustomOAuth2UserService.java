package io.spring.securitysociallogin.service;

import io.spring.securitysociallogin.common.converter.ProviderUserConverter;
import io.spring.securitysociallogin.common.converter.ProviderUserRequest;
import io.spring.securitysociallogin.model.PrincipalUser;
import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.repository.UserRepository;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends AbstractOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  public CustomOAuth2UserService(UserRepository userRepository, UserService userService, ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter) {
    super(userRepository, userService, providerUserConverter);
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    ClientRegistration clientRegistration = userRequest.getClientRegistration();
    OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();
    // 실제 여기에서 인가서버에서 사용자 정보를 가져온다
    // 어떤 프로바이더인지 식별해야 한다
    OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

    ProviderUserRequest providerUserRequest = new ProviderUserRequest(clientRegistration,
        oAuth2User);

    // google or naver or keycloak인지 식별한다
    ProviderUser providerUser = providerUser(providerUserRequest);

    // 여기에서 DB에 저장하면 되는 것 같다
    super.register(providerUser, userRequest);

    // 토큰이 만들어질 때 이 값이 principal로 들어간다
    return new PrincipalUser(providerUser);
  }
}
