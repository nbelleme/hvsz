package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.services.api.UserService;
import io.nbelleme.hvsz.user.User;
import io.nbelleme.persistence.dao.api.UserDao;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by nbelleme on 28/07/2017.
 */
@Service
final class UserServiceImpl implements UserService {

  private UserDao userDao;

  /**
   * Constructor.
   *
   * @param userDao userDao
   */
  UserServiceImpl(UserDao userDao) {
    Objects.requireNonNull(userDao);

    this.userDao = userDao;
  }

  @Override
  public User create(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    return userDao.insert(User.build());
  }

  @Override
  public User get(String id) {
    Objects.requireNonNull(id);

    return userDao.findOne(id);
  }
}
