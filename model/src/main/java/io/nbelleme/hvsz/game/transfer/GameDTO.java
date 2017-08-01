package io.nbelleme.hvsz.game.transfer;

import io.nbelleme.hvsz.api.DPO;
import io.nbelleme.hvsz.game.internal.GameSettings;
import io.nbelleme.hvsz.zone.transfer.SafeZoneDTO;
import io.nbelleme.hvsz.zone.transfer.SupplyZoneDTO;

import java.io.Serializable;
import java.util.List;

public class GameDTO implements Serializable, DPO {

  private static final long serialVersionUID = 2285083455521509726L;

  private String id;
  private Status status;
  private GameSettings config;
  private List<SafeZoneDTO> safeZoneDTOS;
  private List<SupplyZoneDTO> foodSupplies;

  /**
   * GameDTO constructor.
   */
  private GameDTO() {

  }

  /**
   * Build default GameDTO Object.
   *
   * @return new GameDTO
   */
  public static GameDTO build() {
    return new GameDTO();
  }


  // CHECKSTYLE_OFF
  public String getId() {
    return id;
  }

  public GameDTO setId(String id) {
    this.id = id;
    return this;
  }

  public Status getStatus() {
    return status;
  }

  public GameDTO setStatus(Status status) {
    this.status = status;
    return this;
  }

  public GameSettings getConfig() {
    return config;
  }

  public GameDTO setConfig(GameSettings config) {
    this.config = config;
    return this;
  }

  public List<SafeZoneDTO> getSafeZoneDTOS() {
    return safeZoneDTOS;
  }

  public GameDTO setSafeZoneDTOS(List<SafeZoneDTO> safeZoneDTOS) {
    this.safeZoneDTOS = safeZoneDTOS;
    return this;
  }

  public List<SupplyZoneDTO> getFoodSupplies() {
    return foodSupplies;
  }

  public GameDTO setFoodSupplies(List<SupplyZoneDTO> foodSupplies) {
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

    GameDTO gameDTO = (GameDTO) o;

    if (id != null ? !id.equals(gameDTO.id) : gameDTO.id != null) {
      return false;
    }
    if (status != null ? !status.equals(gameDTO.status) : gameDTO.status != null) {
      return false;
    }
    if (config != null ? !config.equals(gameDTO.config) : gameDTO.config != null) {
      return false;
    }
    if (safeZoneDTOS != null ? !safeZoneDTOS.equals(gameDTO.safeZoneDTOS) : gameDTO.safeZoneDTOS != null) {
      return false;
    }
    return foodSupplies != null ? foodSupplies.equals(gameDTO.foodSupplies) : gameDTO.foodSupplies == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (status != null ? status.hashCode() : 0);
    result = 31 * result + (config != null ? config.hashCode() : 0);
    result = 31 * result + (safeZoneDTOS != null ? safeZoneDTOS.hashCode() : 0);
    result = 31 * result + (foodSupplies != null ? foodSupplies.hashCode() : 0);
    return result;
  }

  // CHECKSTYLE_ON
}
