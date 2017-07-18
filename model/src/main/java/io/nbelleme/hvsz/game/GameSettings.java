package io.nbelleme.hvsz.game;


import io.nbelleme.hvsz.api.DTO;

import java.io.Serializable;

public class GameSettings implements Serializable, DTO {

  private static final long serialVersionUID = -6131853646598165461L;

  private static final int DEFAULT_GAME_DURATION = 30;
  private static final int DEFAULT_DIFFICULTY = 1;
  private static final int DEFAULT_HUMAN_TICKETS = 100;
  private static final int DEFAULT_MAX_HUMAN_ON_FIELD = 30;
  private static final int DEFAULT_NB_SAFE_ZONE = 2;
  private static final int DEFAULT_NB_FOOD_ZONE = 2;
  private static final int DEFAULT_FOOD_SUPPLIES = 180;
  private static final int DEFAULT_MAX_FOOD_TRANSFERT = 1;
  private static final int DEFAULT_SAFE_ZONE_DROP_RATE = 30;


  private int gameDuration;
  private int difficulty;
  private int humanTickets;
  private int maxHumansOnField;
  private int nbSafeZones;
  private int startingSafeZoneSupplies;
  private int nbFoodSupplyZones;
  private int nbFoodSupplies;
  private int maximumFoodTransfer;
  // Generated values with difficulty
  private int safezoneDropRate;

  /**
   * GameConfig constructor.
   */
  private GameSettings() {
    this.gameDuration = DEFAULT_GAME_DURATION;
    this.difficulty = DEFAULT_DIFFICULTY;
    this.humanTickets = DEFAULT_HUMAN_TICKETS;
    this.maxHumansOnField = DEFAULT_MAX_HUMAN_ON_FIELD;
    this.nbSafeZones = DEFAULT_NB_SAFE_ZONE;
    this.nbFoodSupplyZones = DEFAULT_NB_FOOD_ZONE;
    this.nbFoodSupplies = DEFAULT_FOOD_SUPPLIES;
    this.maximumFoodTransfer = DEFAULT_MAX_FOOD_TRANSFERT;
    this.safezoneDropRate = DEFAULT_SAFE_ZONE_DROP_RATE;
  }

  /**
   * Return new GameSettings with default values.
   *
   * @return new GameSettings
   */
  public static GameSettings build() {
    return new GameSettings();
  }


  //CHECKSTYLE_OFF
  public int getMaxHumansOnField() {
    return maxHumansOnField;
  }

  public GameSettings setMaxHumansOnField(int maxHumansOnField) {
    this.maxHumansOnField = maxHumansOnField;
    return this;
  }

  public int getGameDuration() {
    return gameDuration;
  }

  public GameSettings setGameDuration(int gameDuration) {
    this.gameDuration = gameDuration;
    return this;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public GameSettings setDifficulty(int difficulty) {
    this.difficulty = difficulty;
    return this;
  }

  public int getHumanTickets() {
    return humanTickets;
  }

  public GameSettings setHumanTickets(int humanTickets) {
    this.humanTickets = humanTickets;
    return this;
  }

  public int getNbSafeZones() {
    return nbSafeZones;
  }

  public GameSettings setNbSafeZones(int nbSafezone) {
    this.nbSafeZones = nbSafezone;
    return this;
  }

  public int getNbFoodSupplyZones() {
    return nbFoodSupplyZones;
  }

  public GameSettings setNbFoodSupplyZones(int nbFoodSupplyZones) {
    this.nbFoodSupplyZones = nbFoodSupplyZones;
    return this;
  }

  public int getNbFoodSupplies() {
    return nbFoodSupplies;
  }

  public GameSettings setNbFoodSupplies(int nbFoodSupplies) {
    this.nbFoodSupplies = nbFoodSupplies;
    return this;
  }

  public int getSafezoneDropRate() {
    return safezoneDropRate;
  }

  public GameSettings setSafezoneDropRate(int safezoneDropRate) {
    this.safezoneDropRate = safezoneDropRate;
    return this;
  }

  public int getMaximumFoodTransfer() {
    return maximumFoodTransfer;
  }

  public GameSettings setMaximumFoodTransfer(int maximumFoodTransfer) {
    this.maximumFoodTransfer = maximumFoodTransfer;
    return this;
  }

  public int getStartingSafeZoneSupplies() {
    return startingSafeZoneSupplies;
  }

  public GameSettings setStartingSafeZoneSupplies(int startingSafeZoneSupplies) {
    this.startingSafeZoneSupplies = startingSafeZoneSupplies;
    return this;
  }

  //CHECKSTYLE_ON
  @Override
  public String toString() {
    return "GameConfig{" +
            "gameDuration=" + gameDuration +
            ", difficulty=" + difficulty +
            ", humanTickets=" + humanTickets +
            ", nbSafeZones=" + nbSafeZones +
            ", nbFoodSupplyZones=" + nbFoodSupplyZones +
            ", nbFoodSupplies=" + nbFoodSupplies +
            ", safezoneDropRate=" + safezoneDropRate +
            ", maximumFoodTransfer=" + maximumFoodTransfer +
            '}';
  }
}
