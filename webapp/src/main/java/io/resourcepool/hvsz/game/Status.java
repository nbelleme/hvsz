package io.resourcepool.hvsz.game;

import io.resourcepool.hvsz.humans.Life;

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
  Integer maxHumansOnField;
  Integer currentHumansOnField;
  Integer remainingHumanTickets;
  Long remainingTime;
  Instant timestampStart;
  Boolean started = false;
  List<Life> lives = new ArrayList<>();
  GameState gameState = GameState.NOT_STARTED;

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

  public Boolean getStarted() {
    return started;
  }

  public Instant getTimestampStart() {
    return timestampStart;
  }

  public void setTimestampStart(Instant timestampStart) {
    this.timestampStart = timestampStart;
  }


  /**
   * set started status, also set gameState to ongoing if true.
   *
   * @param started .
   */
  public void setStarted(Boolean started) {
    this.started = started;
    if (this.started) {
      this.gameState = GameState.ACTIVE;
      this.timestampStart = Instant.now();
    }
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
  public Life getLife(int id) {
    for (Life l : lives) {
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
  public Life getLifeByToken(int token) {
    for (Life l : lives) {
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
}
