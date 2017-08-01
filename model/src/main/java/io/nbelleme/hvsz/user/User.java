package io.nbelleme.hvsz.user;

import io.nbelleme.hvsz.api.DTO;
import org.mongojack.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Document(collection = "user")
public class User implements Serializable, DTO {

  private static final long serialVersionUID = 8910219810353397902L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
  private String username;
  private String password;

  /**
   * Constructor.
   */
  private User() {
    //TODO delete
    username = "username";
    password = "password";
  }

  /**
   * Create default {@link User}.
   *
   * @return new User
   */
  public static User build() {
    return new User();
  }

  //CHECKSTYLE_OFF
  public String getId() {
    return id;
  }

  public User setId(String id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public User setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public User setPassword(String password) {
    this.password = password;
    return this;
  }
  //CHECKSTYLE_ONE
}
