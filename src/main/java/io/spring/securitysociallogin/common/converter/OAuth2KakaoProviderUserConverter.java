package io.spring.securitysociallogin.common.converter;

import io.spring.securitysociallogin.common.enumeration.OAuthProviderEnum;
import io.spring.securitysociallogin.common.util.OAuth2Utils;
import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.model.social.KakaoUser;

public class OAuth2KakaoProviderUserConverter implements
    ProviderUserConverter<ProviderUserRequest, ProviderUser> {

  @Override
  public ProviderUser convert(ProviderUserRequest providerUserRequest) {
    if (!providerUserRequest.clientRegistration().getRegistrationId()
        .equals(OAuthProviderEnum.KAKAO.getProvider())) {
      return null;
    }

    return new KakaoUser(
        OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User()),
        providerUserRequest.oAuth2User(), providerUserRequest.clientRegistration());
  }
}
