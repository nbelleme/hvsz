package io.resourcepool.hvsz.game;

import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.supply.FoodSupply;
import io.resourcepool.hvsz.zombies.ZombieZone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {

  Long id = 1L; // Useless
  Status status = new Status();
  GameSettings config = new GameSettings();
  List<SafeZone> safeZones;
  List<FoodSupply> foodSupplies;
  List<ZombieZone> zombieZones;

  /**
   * Game constructor.
   */
  public Game() {
  }

  /**
   * Set default values to the status if null.
   *
   * @param g Game
   */
  public Game(Game g) {
    this.id = g.id;
    if (g.status != null) {
      this.status = g.status;
    }
    if (g.config != null) {
      this.config = g.config;
    }
    if (g.foodSupplies != null) {
      this.foodSupplies = g.foodSupplies;
    } else {
      this.foodSupplies = new ArrayList<>();
    }
    if (g.safeZones != null) {
      this.safeZones = g.safeZones;
    } else {
      this.safeZones = new ArrayList<>();
    }
    if (g.zombieZones != null) {
      this.zombieZones = g.zombieZones;
    } else {
      this.zombieZones = new ArrayList<>();
    }
  }

  /**
   * Game constructor, set an id.
   *
   * @param id Long
   */
  public Game(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  /**
   * Set the configs.
   *
   * @param config GameConfig
   */
  public void setConfig(GameSettings config) {
    this.config = config;
  }

  public GameSettings getConfig() {
    return config;
  }

  public List<SafeZone> getSafeZones() {
    return safeZones;
  }

  public void setSafeZones(List<SafeZone> safeZones) {
    this.safeZones = safeZones;
  }

  public List<FoodSupply> getFoodSupplies() {
    return foodSupplies;
  }

  public void setFoodSupplies(List<FoodSupply> foodSupplies) {
    this.foodSupplies = foodSupplies;
  }

  public List<ZombieZone> getZombieZones() {
    return zombieZones;
  }

  public void setZombieZones(List<ZombieZone> zombieZones) {
    this.zombieZones = zombieZones;
  }

  // CHECKSTYLE_OFF

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Game game = (Game) o;

    if (id != null ? !id.equals(game.id) : game.id != null) return false;
    if (status != null ? !status.equals(game.status) : game.status != null) return false;
    if (config != null ? !config.equals(game.config) : game.config != null) return false;
    if (safeZones != null ? !safeZones.equals(game.safeZones) : game.safeZones != null) return false;
    if (foodSupplies != null ? !foodSupplies.equals(game.foodSupplies) : game.foodSupplies != null) return false;
    return zombieZones != null ? zombieZones.equals(game.zombieZones) : game.zombieZones == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (config != null ? config.hashCode() : 0);
    result = 31 * result + (safeZones != null ? safeZones.hashCode() : 0);
    result = 31 * result + (foodSupplies != null ? foodSupplies.hashCode() : 0);
    result = 31 * result + (zombieZones != null ? zombieZones.hashCode() : 0);
    return result;
  }

  // CHECKSTYLE_ON
}
