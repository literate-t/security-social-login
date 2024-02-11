package io.spring.securitysociallogin.common.converter;

import io.spring.securitysociallogin.model.ProviderUser;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class DelegatingProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{

  List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> converters;

  public DelegatingProviderUserConverter() {
    List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> providerUserConverters
        = Arrays.asList(
        new UserDetailsProviderConverter(),
        new OAuth2GoogleProviderUserConverter(),
        new OAuth2NaverProviderUserConverter(),
        new OAuth2KakaoProviderUserConverter());
    converters = List.copyOf(providerUserConverters); // same as Collections.unmodifiableList
  }

  @Override
  public ProviderUser convert(ProviderUserRequest providerUserRequest) {
    Assert.notNull(providerUserRequest, "providerUserRequest can't be null");

    for (var converter : converters) {
      ProviderUser providerUser = converter.convert(providerUserRequest);
      if (null != providerUser) {
        return providerUser;
      }
    }

    return null;
  }
}
