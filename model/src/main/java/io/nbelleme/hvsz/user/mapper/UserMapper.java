package io.nbelleme.hvsz.user.mapper;

import io.nbelleme.hvsz.user.internal.User;
import io.nbelleme.hvsz.user.transfer.UserDTO;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
public class UserMapper {

  public static UserDTO toDTO(User user) {
    Objects.requireNonNull(user);
    return UserDTO.build()
                  .setId(user.getId());
  }

  public static User fromDTO(UserDTO userDTO) {
    Objects.requireNonNull(userDTO);
    return User.build()
               .setId(userDTO.getId());
  }
}
