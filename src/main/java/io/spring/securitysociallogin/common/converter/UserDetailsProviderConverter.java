package io.spring.securitysociallogin.common.converter;

import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.model.user.FormUser;
import io.spring.securitysociallogin.model.user.User;

public class UserDetailsProviderConverter implements
    ProviderUserConverter<ProviderUserRequest, ProviderUser> {

  @Override
  public ProviderUser convert(ProviderUserRequest providerUserRequest) {
    if (null == providerUserRequest.user()) {
      return null;
    }

    User user = providerUserRequest.user();

    return FormUser.builder()
        .id(user.getId())
        .password(user.getPassword())
        .email(user.getEmail())
        .username(user.getUsername())
        .authorities(user.getAuthorities())
        .provider("none")
        .build();
  }
}
