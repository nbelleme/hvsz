package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.mapper.api.UserMapper;
import io.nbelleme.hvsz.user.internal.User;
import io.nbelleme.hvsz.user.transfer.UserDTO;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
@Component
public class UserMapperImpl implements UserMapper<User, UserDTO> {

  private Mapper mapper;

  /**
   * Constructor.
   *
   * @param mapper mapper
   */
  UserMapperImpl(Mapper mapper) {
    this.mapper = Objects.requireNonNull(mapper);
  }

  @Override
  public UserDTO toDTO(User user) {
    Objects.requireNonNull(user);
    return mapper.map(user, UserDTO.class);
  }

  @Override
  public User fromDTO(UserDTO userDTO) {
    Objects.requireNonNull(userDTO);
    return mapper.map(userDTO, User.class);
  }
}
