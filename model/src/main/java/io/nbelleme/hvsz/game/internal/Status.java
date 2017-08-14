package io.nbelleme.hvsz.game.internal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.nbelleme.hvsz.api.DPO;
import io.nbelleme.hvsz.human.internal.Human;
import org.mongojack.Id;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import static io.nbelleme.hvsz.game.internal.GameState.ACTIVE;
import static io.nbelleme.hvsz.game.internal.GameState.HUMAN_VICTORY;
import static io.nbelleme.hvsz.game.internal.GameState.NOT_STARTED;
import static io.nbelleme.hvsz.game.internal.GameState.PAUSED;
import static io.nbelleme.hvsz.game.internal.GameState.STOPPED;
import static io.nbelleme.hvsz.game.internal.GameState.ZOMBIE_VICTORY;

public class Status implements Serializable, DPO {

  private static final long serialVersionUID = -3433632584769693093L;

  private String id;
  private int currentHumansOnField;
  private int remainingHumanTickets;
  private long remainingTime;
  private transient Instant timestampStart;
  private List<Human> lives;
  private GameState state;

  /**
   * Default constructor.
   */
  private Status() {
    state = GameState.NOT_STARTED;
  }

  /**
   * Build generic Status.
   *
   * @return new Status
   */
  public static Status build() {
    return new Status();
  }

  //CHECKSTYLE_OFF

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public int getCurrentHumansOnField() {
    return currentHumansOnField;
  }

  public Status setCurrentHumansOnField(int currentHumansOnField) {
    this.currentHumansOnField = currentHumansOnField;
    return this;
  }

  public int getRemainingHumanTickets() {
    return remainingHumanTickets;
  }

  public Status setRemainingHumanTickets(int remainingHumanTickets) {
    this.remainingHumanTickets = remainingHumanTickets;
    return this;
  }

  public long getRemainingTime() {
    return remainingTime;
  }

  public Status setRemainingTime(long remainingTime) {
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

  public List<Human> getLives() {
    return lives;
  }

  public Status setLives(List<Human> lives) {
    this.lives = lives;
    return this;
  }

  public GameState getState() {
    return state;
  }

  public Status setState(GameState gameState) {
    this.state = gameState;
    return this;
  }

  //CHECKSTYLE_ON


  /**
   * get life by id.
   *
   * @param id .
   * @return .
   */
  public Human getLifeById(int id) {
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
  public Human getLifeByToken(int token) {
    return lives.stream()
                .filter(l -> l.getToken() == token)
                .findFirst()
                .orElse(null);
  }

  @JsonIgnore
  public boolean isReadyToStart() {
    return isOver() || isNotStarted();
  }

  @JsonIgnore
  public boolean isOver() {
    return state.equals(NOT_STARTED) || state.equals(HUMAN_VICTORY) || state.equals(ZOMBIE_VICTORY);
  }

  @JsonIgnore
  public boolean isOngoing() {
    return state.equals(ACTIVE) || state.equals(PAUSED);
  }

  @JsonIgnore
  public boolean isActive() {
    return state.equals(ACTIVE);
  }

  @JsonIgnore
  public boolean isNotStarted() {
    return state.equals(NOT_STARTED);
  }

  @JsonIgnore
  public boolean isOngoingOrIdle() {
    return isOngoing() || isNotStarted();
  }

  @JsonIgnore
  public boolean isPaused() {
    return state.equals(PAUSED);
  }

  @JsonIgnore
  public boolean isStopped() {
    return state.equals(STOPPED);
  }

  /**
   * Set life in place of a previous life content.
   *
   * @param id    the life id
   * @param human the new life object
   * @return this
   */
  public Status setLife(Long id, Human human) {
    for (int i = 0; i < lives.size(); i++) {
      Human l = lives.get(i);
      if (l.getId().equals(id)) {
        lives.set(i, human);
      }
    }
    return this;
  }

}
