package io.spring.securitysociallogin.service;

import io.spring.securitysociallogin.common.converter.ProviderUserConverter;
import io.spring.securitysociallogin.common.converter.ProviderUserRequest;
import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.model.user.User;
import io.spring.securitysociallogin.repository.UserRepository;
import lombok.Getter;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

  private final String REGISTRATION_ID_GOOGLE = "google";
  private final String REGISTRATION_ID_NAVER = "naver";
  private final String REGISTRATION_ID_KEYCLOAK = "keycloak";

  private final UserRepository userRepository;
  private final UserService userService;
  private final ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

  protected AbstractOAuth2UserService(UserRepository userRepository, UserService userService,
      ProviderUserConverter providerUserConverter) {
    this.userRepository = userRepository;
    this.userService = userService;
    this.providerUserConverter = providerUserConverter;
  }

  // 컨버터를 이용하 각 프로바이더를 식별한다
  // 이 방법은 스프링에서 많이 쓰는 방법으로 위임 클래스를 만들고
  // 위임 클래스 안에서 미리 만들어놓은 클래스들을 반복문을 돌려 적절한 인스턴스를 찾는다
  protected ProviderUser providerUser(ProviderUserRequest providerUserRequest) {
    return providerUserConverter.convert(providerUserRequest);
  }

  protected void register(ProviderUser providerUser, OAuth2UserRequest userRequest) {
    User user = userRepository.findByUsername(providerUser.getUsername());
    if (null == user) {
      String registrationId = userRequest.getClientRegistration().getRegistrationId();
      userService.register(registrationId, providerUser);
    } else {
      System.out.println("Registered user = " + user);
    }
  }
}
