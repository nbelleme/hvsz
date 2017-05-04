package io.resourcepool.hvsz.game;

import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.supply.FoodSupply;
import io.resourcepool.hvsz.zombies.ZombieZone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Game implements Serializable {

  Long id = 0L; // Useless
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


  /**
   * check at least one zone has resources left.
   *
   * @return .
   */
  public Boolean checkSafeZoneLeft() {
    for (SafeZone s : safeZones) {
      if (s.getLevel() > 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * get supply zonr by id.
   *
   * @param id .
   * @return .
   */
  public FoodSupply getSupplyZoneById(int id) {
    for (FoodSupply z : foodSupplies) {
      if (z.getId() == id) {
        return z;
      }
    }
    return null;
  }

  /**
   * get safe zone by id.
   *
   * @param id .
   * @return .
   */
  public SafeZone getSafeZoneById(int id) {
    for (SafeZone z : safeZones) {
      if (z.getId() == id) {
        return z;
      }
    }
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Game game = (Game) o;

    return getId().equals(game.getId());
  }

  @Override
  public int hashCode() {
    return getId().hashCode();
  }
}
