package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.controller.api.UserController;
import io.nbelleme.hvsz.services.api.UserService;
import io.nbelleme.hvsz.user.User;
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
final class UserControllerImpl implements UserController {

  private UserService userService;

  /**
   * Constructor.
   *
   * @param userService userService
   */
  UserControllerImpl(UserService userService) {
    Objects.requireNonNull(userService);

    this.userService = userService;
  }

  @Override
  @PostMapping("/create")
  public User create() {
    return userService.create("username", "password");
  }

  @Override
  @GetMapping("/get/{id}")
  public User get(@PathVariable("id") String id) {
    Objects.requireNonNull(id);

    return userService.get(id);
  }


}
