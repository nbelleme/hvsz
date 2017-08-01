package io.nbelleme.hvsz.controller.api;

import io.nbelleme.hvsz.user.User;

/**
 * Created by nbelleme on 28/07/2017.
 */
public interface UserController {

  /**
   * Create new User.
   *
   * @return user created
   */
  User create();

  /**
   * Get user.
   *
   * @param id id
   * @return user
   */
  User get(String id);
}
