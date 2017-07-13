package io.resourcepool.hvsz.game;

import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.supply.FoodSupply;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {

  private Long id;
  private Status status;
  private GameSettings config;
  private List<SafeZone> safeZones;
  private List<FoodSupply> foodSupplies;

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

  public GameSettings getConfig() {
    return config;
  }

  /**
   * Set the configs.
   *
   * @param config GameConfig
   */
  public void setConfig(GameSettings config) {
    this.config = config;
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
  // CHECKSTYLE_OFF


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Game game = (Game) o;

    if (id != null ? !id.equals(game.id) : game.id != null) {
      return false;
    }
    if (status != null ? !status.equals(game.status) : game.status != null) {
      return false;
    }
    if (config != null ? !config.equals(game.config) : game.config != null) {
      return false;
    }
    if (safeZones != null ? !safeZones.equals(game.safeZones) : game.safeZones != null) {
      return false;
    }
    return foodSupplies != null ? foodSupplies.equals(game.foodSupplies) : game.foodSupplies == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (config != null ? config.hashCode() : 0);
    result = 31 * result + (safeZones != null ? safeZones.hashCode() : 0);
    result = 31 * result + (foodSupplies != null ? foodSupplies.hashCode() : 0);
    return result;
  }

  // CHECKSTYLE_ON
}
