package io.resourcepool.hvsz.persistance.models;

import java.io.Serializable;

public class Game implements Serializable {
  Long id = 0L; //uselss
  GameStatus status;
  GameConfig config;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GameStatus getStatus() {
    return status;
  }

  public void setStatus(GameStatus status) {
    this.status = status;
  }

  public GameConfig getConfig() {
    return config;
  }

  public void setConfig(GameConfig config) {
    this.config = config;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Game game = (Game) o;

    return getId().equals(game.getId());
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
  }
}
