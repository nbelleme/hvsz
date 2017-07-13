package io.resourcepool.hvsz.game;


import java.io.Serializable;

public class GameSettings implements Serializable {

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

  public int getMaxHumansOnField() {
    return maxHumansOnField;
  }

  public void setMaxHumansOnField(int maxHumansOnField) {
    this.maxHumansOnField = maxHumansOnField;
  }

  public int getGameDuration() {
    return gameDuration;
  }

  public void setGameDuration(int gameDuration) {
    this.gameDuration = gameDuration;
  }

  public int getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
  }

  public int getHumanTickets() {
    return humanTickets;
  }

  public void setHumanTickets(int humanTickets) {
    this.humanTickets = humanTickets;
  }

  public int getNbSafeZones() {
    return nbSafeZones;
  }

  public void setNbSafeZones(int nbSafezone) {
    this.nbSafeZones = nbSafezone;
  }

  public int getNbFoodSupplyZones() {
    return nbFoodSupplyZones;
  }

  public void setNbFoodSupplyZones(int nbFoodSupplyZones) {
    this.nbFoodSupplyZones = nbFoodSupplyZones;
  }

  public int getNbFoodSupplies() {
    return nbFoodSupplies;
  }

  public void setNbFoodSupplies(int nbFoodSupplies) {
    this.nbFoodSupplies = nbFoodSupplies;
  }

  public int getSafezoneDropRate() {
    return safezoneDropRate;
  }

  public void setSafezoneDropRate(int safezoneDropRate) {
    this.safezoneDropRate = safezoneDropRate;
  }

  public int getMaximumFoodTransfer() {
    return maximumFoodTransfer;
  }

  public void setMaximumFoodTransfer(int maximumFoodTransfer) {
    this.maximumFoodTransfer = maximumFoodTransfer;
  }

  public int getStartingSafeZoneSupplies() {
    return startingSafeZoneSupplies;
  }

  public void setStartingSafeZoneSupplies(int startingSafeZoneSupplies) {
    this.startingSafeZoneSupplies = startingSafeZoneSupplies;
  }

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
