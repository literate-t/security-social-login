package io.spring.securitysociallogin.enumeration;

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
