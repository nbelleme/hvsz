package io.nbelleme.persistence.dao.api;

import io.nbelleme.hvsz.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by nbelleme on 28/07/2017.
 */
public interface UserDao extends MongoRepository<User, String> {
  String COLLECTION_NAME = "user";
}
