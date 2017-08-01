package io.nbelleme.hvsz.game.internal;

import io.nbelleme.hvsz.api.DPO;
import io.nbelleme.hvsz.zone.internal.SafeZone;
import io.nbelleme.hvsz.zone.internal.SupplyZone;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Game implements Serializable, DPO {

  private static final long serialVersionUID = 3753312791177172327L;

  private String id;
  private Status status;
  private GameSettings config;
  private List<SafeZone> safeZones;
  private List<SupplyZone> foodSupplies;

  /**
   * GameDTO constructor.
   */
  private Game() {
    id = UUID.randomUUID().toString();
    status = Status.build();
    config = GameSettings.build();

  }

  /**
   * Build default GameDTO Object.
   *
   * @return new GameDTO
   */
  public static Game build() {
    return new Game();
  }

// CHECKSTYLE_OFF

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public String getId() {
    return id;
  }

  public Game setId(String id) {
    this.id = id;
    return this;
  }

  public Status getStatus() {
    return status;
  }

  public Game setStatus(Status status) {
    this.status = status;
    return this;
  }

  public GameSettings getConfig() {
    return config;
  }

  public Game setConfig(GameSettings config) {
    this.config = config;
    return this;
  }

  public List<SafeZone> getSafeZones() {
    return safeZones;
  }

  public Game setSafeZones(List<SafeZone> safeZones) {
    this.safeZones = safeZones;
    return this;
  }

  public List<SupplyZone> getFoodSupplies() {
    return foodSupplies;
  }

  public Game setFoodSupplies(List<SupplyZone> foodSupplies) {
    this.foodSupplies = foodSupplies;
    return this;
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
