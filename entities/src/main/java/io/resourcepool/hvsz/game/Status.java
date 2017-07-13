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

  /**
   * Default constructor.
   */
  private Status() {

  }


  /**
   * Build generic Status.
   *
   * @return new Status
   */
  public static Status build() {
    return new Status();
  }

  public Integer getMaxHumansOnField() {
    return maxHumansOnField;
  }

  //CHECKSTYLE_OFF
  public Status setMaxHumansOnField(Integer maxHumansOnField) {
    this.maxHumansOnField = maxHumansOnField;
    return this;
  }

  public Integer getCurrentHumansOnField() {
    return currentHumansOnField;
  }

  public Status setCurrentHumansOnField(Integer currentHumansOnField) {
    this.currentHumansOnField = currentHumansOnField;
    return this;
  }

  public Long getRemainingTime() {
    return remainingTime;
  }

  public Status setRemainingTime(Long remainingTime) {
    this.remainingTime = remainingTime;
    return this;
  }

  public Instant getTimestampStart() {
    return timestampStart;
  }

  public Status setTimestampStart(Instant timestampStart) {
    this.timestampStart = timestampStart;
    return this;
  }

  public Integer getRemainingHumanTickets() {
    return remainingHumanTickets;
  }

  public Status setRemainingHumanTickets(Integer remainingHumanTickets) {
    this.remainingHumanTickets = remainingHumanTickets;
    return this;
  }

  public List<Life> getLives() {
    return this.lives;
  }

  public Status setLives(List<Life> lives) {
    this.lives = lives;
    return this;
  }

  public GameState getGameState() {
    return gameState;
  }

  public Status setGameState(GameState gameState) {
    this.gameState = gameState;
    return this;
  }

  //CHECKSTYLE_ON


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
    return gameState.equals(NOT_STARTED) || gameState.equals(HUMAN_VICTORY) || gameState.equals(ZOMBIE_VICTORY);
  }

  public boolean isOngoing() {
    return gameState.equals(ACTIVE) || gameState.equals(PAUSED);
  }

  public boolean isActive() {
    return gameState.equals(ACTIVE);
  }

  public boolean isNotStarted() {
    return gameState.equals(NOT_STARTED);
  }

  public boolean isOngoingOrIdle() {
    return isOngoing() || isNotStarted();
  }

  public boolean isPaused() {
    return gameState.equals(PAUSED);
  }

  /**
   * Set life in place of a previous life content.
   *
   * @param id   the life id
   * @param life the new life object
   * @return this
   */
  public Status setLife(Long id, Life life) {
    for (int i = 0; i < lives.size(); i++) {
      Life l = lives.get(i);
      if (l.getId().equals(id)) {
        lives.set(i, life);
      }
    }
    return this;
  }

}
