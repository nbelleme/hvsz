package io.nbelleme.hvsz.user.mapper;

import io.nbelleme.hvsz.user.internal.User;
import io.nbelleme.hvsz.user.transfer.UserDTO;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
public class UserMapper {

  /**
   * Map {@link User} to {@link UserDTO}.
   *
   * @param user user object to map
   * @return userDTO mapped
   */
  public static UserDTO toDTO(User user) {
    Objects.requireNonNull(user);
    return UserDTO.build()
                  .setId(user.getId());
  }

  /**
   * Map {@link User} to {@link UserDTO}.
   *
   * @param userDTO userDTO object to unmap
   * @return userDTO unmapped
   */
  public static User fromDTO(UserDTO userDTO) {
    Objects.requireNonNull(userDTO);
    return User.build()
               .setId(userDTO.getId());
  }
}
