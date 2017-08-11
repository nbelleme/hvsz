package io.nbelleme.hvsz.mapper.api;

import io.nbelleme.hvsz.user.internal.User;
import io.nbelleme.hvsz.user.transfer.UserDTO;

public interface UserMapper {
  /**
   * Map {@link User} to {@link UserDTO}.
   *
   * @param user user object to map
   * @return userDTO mapped
   */
  UserDTO toDTO(User user);

  /**
   * Map {@link User} to {@link UserDTO}.
   *
   * @param userDTO userDTO object to unmap
   * @return userDTO unmapped
   */
  User fromDTO(UserDTO userDTO);
}
