package io.spring.securitysociallogin.common.enumeration;

public enum OAuthProviderEnum {
  GOOGLE("google"),
  NAVER("naver"),
  KAKAO("kakao");

  private final String provider;

  OAuthProviderEnum(String provider) {
    this.provider = provider;
  }

  public String getProvider() {
    return provider;
  }
}
