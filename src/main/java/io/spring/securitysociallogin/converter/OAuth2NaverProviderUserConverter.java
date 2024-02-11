package io.spring.securitysociallogin.converter;

import io.spring.securitysociallogin.enumeration.OAuthProviderEnum;
import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.model.social.NaverUser;
import io.spring.securitysociallogin.util.OAuth2Utils;

public class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

  @Override
  public ProviderUser convert(ProviderUserRequest providerUserRequest) {
    if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuthProviderEnum.NAVER.getProvider())) {
      return null;
    }

    return new NaverUser(OAuth2Utils.getSubAttributes(providerUserRequest.oAuth2User(), "response"),
        providerUserRequest.oAuth2User(), providerUserRequest.clientRegistration());
  }
}
