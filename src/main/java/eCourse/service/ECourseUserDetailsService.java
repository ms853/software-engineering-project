package ecourse.service;

import ecourse.ECourseUserDetails;
import ecourse.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ECourseUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepo;

  @Override
  public UserDetails loadUserByUsername(String username) {
    ecourse.domain.User domainUser = userRepo.findByEmail(username);

    if (domainUser != null) {
      final boolean enabled = true;
      final boolean accountNonExpired = true;
      final boolean credentialsNonExpired = true;
      final boolean accountNonLocked = true;

      return new ECourseUserDetails(domainUser, enabled, accountNonExpired, credentialsNonExpired,
          accountNonLocked, AuthorityUtils.createAuthorityList("ROLE_PRE_AUTH"));
    } else {
      throw new UsernameNotFoundException("User with username '" + username + "' not found.");
    }
  }

}
