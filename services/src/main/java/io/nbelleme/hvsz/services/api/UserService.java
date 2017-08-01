package io.nbelleme.hvsz.services.api;

import io.nbelleme.hvsz.user.User;

/**
 * Created by nbelleme on 28/07/2017.
 */
public interface UserService {

  /**
   * Create new {@link User}.
   *
   * @param username new User's username
   * @param password new User's password
   * @return new User
   */
  User create(String username, String password);

  /**
   * Return {@link User} from database.
   *
   * @param id user to return's id
   * @return User
   */
  User get(String id);


}
