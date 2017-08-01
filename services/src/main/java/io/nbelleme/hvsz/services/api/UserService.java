package io.nbelleme.hvsz.services.api;

import io.nbelleme.hvsz.user.internal.User;

/**
 * Created by nbelleme on 28/07/2017.
 */
public interface UserService {

  /**
   * Create new {@link User}.
   *
   * @param username new UserDTO's username
   * @param password new UserDTO's password
   * @return new UserDTO
   */
  User create(String username, String password);

  /**
   * Return {@link User} from database.
   *
   * @param id user to return's id
   * @return UserDTO
   */
  User get(String id);


}
