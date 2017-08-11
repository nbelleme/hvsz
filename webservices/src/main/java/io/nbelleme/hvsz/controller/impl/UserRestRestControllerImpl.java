package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.controller.api.UserRestController;
import io.nbelleme.hvsz.mapper.api.GenericMapper;
import io.nbelleme.hvsz.services.api.UserService;
import io.nbelleme.hvsz.user.internal.User;
import io.nbelleme.hvsz.user.transfer.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Created by nbelleme on 28/07/2017.
 */
@RestController
@RequestMapping("/api/user")
final class UserRestRestControllerImpl implements UserRestController {

  private UserService userService;
  private GenericMapper<User, UserDTO> userMapper;

  /**
   * Constructor.
   *
   * @param userService userService
   * @param userMapper  userMapper
   */
  UserRestRestControllerImpl(UserService userService, GenericMapper<User, UserDTO> userMapper) {
    this.userService = Objects.requireNonNull(userService);
    this.userMapper = Objects.requireNonNull(userMapper);
  }

  @Override
  @PostMapping("/create")
  public UserDTO create() {
    User user = userService.create("username", "password");
    return userMapper.toDTO(user);
  }

  @Override
  @GetMapping("/get/{id}")
  public UserDTO get(@PathVariable("id") String id) {
    Objects.requireNonNull(id);

    User user = userService.get(id);
    return userMapper.toDTO(user);
  }


}
