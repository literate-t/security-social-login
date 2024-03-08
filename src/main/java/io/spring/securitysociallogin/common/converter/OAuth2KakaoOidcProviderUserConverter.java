package io.spring.securitysociallogin.common.converter;

import io.spring.securitysociallogin.common.util.OAuth2Utils;
import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.model.social.KakaoUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import static io.spring.securitysociallogin.common.enumeration.OAuthProviderEnum.KAKAO;

public class OAuth2KakaoOidcProviderUserConverter implements
    ProviderUserConverter<ProviderUserRequest, ProviderUser> {

  @Override
  public ProviderUser convert(ProviderUserRequest providerUserRequest) {

    if (!providerUserRequest.clientRegistration().getRegistrationId()
        .equals(KAKAO.getProvider()) && !(providerUserRequest.oAuth2User() instanceof OidcUser)) {
      return null;
    }

    return new KakaoUser(
        OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User()),
        providerUserRequest.oAuth2User(), providerUserRequest.clientRegistration());
  }
}
