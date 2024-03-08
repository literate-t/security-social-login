package io.spring.securitysociallogin.common.converter;

import io.spring.securitysociallogin.common.util.OAuth2Utils;
import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.model.social.GoogleUser;

import static io.spring.securitysociallogin.common.enumeration.OAuthProviderEnum.GOOGLE;

public class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {

  @Override
  public ProviderUser convert(ProviderUserRequest providerUserRequest) {

    if (!providerUserRequest.clientRegistration().getRegistrationId().equals(GOOGLE.getProvider())) {
      return null;
    }

    return new GoogleUser(OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User()),
        providerUserRequest.oAuth2User(), providerUserRequest.clientRegistration());
  }
}
