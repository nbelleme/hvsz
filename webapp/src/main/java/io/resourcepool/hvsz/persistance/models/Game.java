package io.resourcepool.hvsz.persistance.models;

/**
 * Created by root on 26/04/17.
 */
public class Game {
  Long id = 0L;
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
}
