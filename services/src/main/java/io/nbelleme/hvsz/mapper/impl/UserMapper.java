package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.user.internal.User;
import io.nbelleme.hvsz.user.transfer.UserDTO;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
@Component
public class UserMapper {

  private Mapper mapper;

  /**
   * Constructor.
   * @param mapper mapper
   */
   UserMapper(Mapper mapper) {
    this.mapper = Objects.requireNonNull(mapper);
  }

  /**
   * Map {@link User} to {@link UserDTO}.
   *
   * @param user user object to map
   * @return userDTO mapped
   */
  public UserDTO toDTO(User user) {
    Objects.requireNonNull(user);
    return mapper.map(user, UserDTO.class);
  }

  /**
   * Map {@link User} to {@link UserDTO}.
   *
   * @param userDTO userDTO object to unmap
   * @return userDTO unmapped
   */
  public User fromDTO(UserDTO userDTO) {
    Objects.requireNonNull(userDTO);
    return mapper.map(userDTO, User.class);
  }
}
