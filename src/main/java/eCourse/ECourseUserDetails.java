package ecourse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ECourseUserDetails extends User {

  private static final long serialVersionUID = 4361025898690187112L;

  private long userId;

  /**
   * Create a new details object.
   * 
   * @param userId The ID of the {@link ecourse.domain.User}.
   * @param username The username of the User - their email address.
   * @param password The password of the User - their hashed password.
   * @param enabled Whether or not the User is enabled.
   * @param accountNonExpired Whether or not the User's account is expired.
   * @param credentialsNonExpired Whether or not the User's credentials have expired.
   * @param accountNonLocked Whether or not the User's account is locked.
   * @param authorities The authorities granted to the User.
   */
  public ECourseUserDetails(long userId, String username, String password, boolean enabled,
      boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
        authorities);
    this.userId = userId;
  }

  public ECourseUserDetails(ecourse.domain.User domainUser, boolean enabled,
      boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
      Collection<? extends GrantedAuthority> authorities) {
    this(domainUser.getId(), domainUser.getEmail(), domainUser.getPasswordHash(), enabled,
        accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
  }

  @Override
  public boolean equals(Object object) {
    return super.equals(object);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  public long getUserId() {
    return userId;
  }

}
