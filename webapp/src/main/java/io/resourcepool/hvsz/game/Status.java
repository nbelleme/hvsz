package io.resourcepool.hvsz.game;

import io.resourcepool.hvsz.humans.Human;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
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
  private Instant timestampStart;
  private List<Human> lives = new ArrayList<>();
  private GameState gameState = GameState.NOT_STARTED;

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

  public List<Human> getLives() {
    return this.lives;
  }

  public void setLives(List<Human> lives) {
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
  public Human getLife(int id) {
    for (Human l : lives) {
      if (l.getId() == id) {
        return l;
      }
    }
    return null;
  }

  /**
   * get life by id.
   *
   * @param token .
   * @return .
   */
  public Human getLifeByToken(int token) {
    for (Human l : lives) {
      if (l.getToken() == token) {
        return l;
      }
    }
    return null;
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
   * Set human in place of a previous human content.
   *
   * @param id   the human id
   * @param human the new human object
   */
  public void setLife(Long id, Human human) {
    for (int i = 0; i < lives.size(); i++) {
      Human l = lives.get(i);
      if (l.getId() == id) {
        lives.set(i, human);
      }
    }
  }
}
