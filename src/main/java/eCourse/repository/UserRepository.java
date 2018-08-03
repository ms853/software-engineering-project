package ecourse.repository;

import ecourse.domain.User;
import ecourse.domain.UserRole;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

  List<User> findByRole(UserRole role);

  User findByEmail(String email);

  Page<User> findAll(Pageable pageable);

  @Query("SELECT u FROM User u WHERE (LOWER(u.email) = LOWER(?1)) OR (LOWER(u.name) = LOWER(?1))")
  Page<User> findUsers(String emailOrName, Pageable pageable);

}
