package io.spring.securitysociallogin.service;

import io.spring.securitysociallogin.common.converter.ProviderUserConverter;
import io.spring.securitysociallogin.common.converter.ProviderUserRequest;
import io.spring.securitysociallogin.model.PrincipalUser;
import io.spring.securitysociallogin.model.ProviderUser;
import io.spring.securitysociallogin.model.user.User;
import io.spring.securitysociallogin.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService extends AbstractOAuth2UserService implements
    UserDetailsService {

  private final UserRepository userRepository;


  protected CustomUserDetailsService(
      UserRepository userRepository, UserService userService,
      ProviderUserConverter providerUserConverter) {
    super(userRepository, userService, providerUserConverter);

    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    if (null == user) {
      user = User.builder().id("1").username("user1").password("{noop}1234")
          .authorities(AuthorityUtils.createAuthorityList("ROLE_USER"))
          .email("user@mail.com")
          .build();
    }

    ProviderUserRequest providerUserRequest = new ProviderUserRequest(user);
    ProviderUser providerUser = providerUser(providerUserRequest);

    // 토큰이 만들어질 때 이 값이 principal로 들어간다
    return new PrincipalUser(providerUser);
  }
}
