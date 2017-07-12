package io.resourcepool.hvsz.game;

import io.resourcepool.hvsz.humans.Life;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static io.resourcepool.hvsz.game.GameState.ACTIVE;
import static io.resourcepool.hvsz.game.GameState.HUMAN_VICTORY;
import static io.resourcepool.hvsz.game.GameState.NOT_STARTED;
import static io.resourcepool.hvsz.game.GameState.PAUSED;
import static io.resourcepool.hvsz.game.GameState.ZOMBIE_VICTORY;

public class Status implements Serializable {
  private Integer maxHumansOnField;
  private Integer currentHumansOnField;
  private Integer remainingHumanTickets;
  private Long remainingTime;
  private transient Instant timestampStart;
  private List<Life> lives;
  private GameState gameState;

  public Integer getMaxHumansOnField() {
    return maxHumansOnField;
  }

  public void setMaxHumansOnField(Integer maxHumansOnField) {
    this.maxHumansOnField = maxHumansOnField;
  }

  public Integer getCurrentHumansOnField() {
    return currentHumansOnField;
  }

  public void setCurrentHumansOnField(Integer currentHumansOnField) {
    this.currentHumansOnField = currentHumansOnField;
  }

  public Long getRemainingTime() {
    return remainingTime;
  }

  public void setRemainingTime(Long remainingTime) {
    this.remainingTime = remainingTime;
  }

  public Instant getTimestampStart() {
    return timestampStart;
  }

  public void setTimestampStart(Instant timestampStart) {
    this.timestampStart = timestampStart;
  }

  public Integer getRemainingHumanTickets() {
    return remainingHumanTickets;
  }

  public void setRemainingHumanTickets(Integer remainingHumanTickets) {
    this.remainingHumanTickets = remainingHumanTickets;
  }

  public List<Life> getLives() {
    return this.lives;
  }

  public void setLives(List<Life> lives) {
    this.lives = lives;
  }

  public GameState getGameState() {
    return gameState;
  }

  public void setGameState(GameState gameState) {
    this.gameState = gameState;
  }


  /**
   * get life by id.
   *
   * @param id .
   * @return .
   */
  public Life getLifeById(int id) {
    return lives.stream()
        .filter(life -> life.getId() == id)
        .findFirst()
        .orElse(null);
  }

  /**
   * get life by id.
   *
   * @param token .
   * @return .
   */
  public Life getLifeByToken(int token) {
    return lives.stream()
        .filter(l -> l.getToken() == token)
        .findFirst()
        .orElse(null);
  }

  public boolean isReadyToStart() {
    return isOver() || isNotStarted();
  }

  public boolean isOver() {
    return NOT_STARTED.equals(gameState) || HUMAN_VICTORY.equals(gameState) || ZOMBIE_VICTORY.equals(gameState);
  }

  public boolean isOngoing() {
    return ACTIVE.equals(gameState) || PAUSED.equals(gameState);
  }

  public boolean isActive() {
    return ACTIVE.equals(gameState);
  }

  public boolean isNotStarted() {
    return NOT_STARTED.equals(gameState);
  }

  public boolean isOngoingOrIdle() {
    return isOngoing() || isNotStarted();
  }

  public boolean isPaused() {
    return PAUSED.equals(gameState);
  }

  /**
   * Set life in place of a previous life content.
   *
   * @param id   the life id
   * @param life the new life object
   */
  public void setLife(Long id, Life life) {
    for (int i = 0; i < lives.size(); i++) {
      Life l = lives.get(i);
      if (l.getId().equals(id)) {
        lives.set(i, life);
      }
    }
  }
}
