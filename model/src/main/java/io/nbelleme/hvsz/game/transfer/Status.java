package io.nbelleme.hvsz.game.transfer;

import io.nbelleme.hvsz.api.DPO;

import java.io.Serializable;

public class Status implements Serializable, DPO {

  private static final long serialVersionUID = -3433632584769693093L;

  private int maxHumansOnField;
  private int currentHumansOnField;
  private int remainingHumanTickets;
  private long remainingTime;

  /**
   * Default constructor.
   */
  private Status() {
  }

  //CHECKSTYLE_OFF
  public int getMaxHumansOnField() {
    return maxHumansOnField;
  }

  public Status setMaxHumansOnField(int maxHumansOnField) {
    this.maxHumansOnField = maxHumansOnField;
    return this;
  }

  public int getCurrentHumansOnField() {
    return currentHumansOnField;
  }

  public Status setCurrentHumansOnField(int currentHumansOnField) {
    this.currentHumansOnField = currentHumansOnField;
    return this;
  }

  public long getRemainingTime() {
    return remainingTime;
  }

  public Status setRemainingTime(long remainingTime) {
    this.remainingTime = remainingTime;
    return this;
  }


  public int getRemainingHumanTickets() {
    return remainingHumanTickets;
  }

  public Status setRemainingHumanTickets(int remainingHumanTickets) {
    this.remainingHumanTickets = remainingHumanTickets;
    return this;
  }
  //CHECKSTYLE_ON

}
