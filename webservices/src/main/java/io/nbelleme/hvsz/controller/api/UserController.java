package io.nbelleme.hvsz.controller.api;

import io.nbelleme.hvsz.user.transfer.UserDTO;

/**
 * Created by nbelleme on 28/07/2017.
 */
public interface UserController {

  /**
   * Create new UserDTO.
   *
   * @return user created
   */
  UserDTO create();

  /**
   * Get user.
   *
   * @param id id
   * @return user
   */
  UserDTO get(String id);
}
