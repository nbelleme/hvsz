package io.nbelleme.hvsz.zone.transfer;

import java.io.Serializable;

public class SafeZoneDTO implements Serializable {

  private static final long serialVersionUID = -8759367013773257550L;

  private static final String ZONE_NAME = "ZoneDTO de sécurié n° ";

  private Long id;
  private int level;
  private int capacity;
  private String name;
  private Boolean destroyed = false;

  /**
   * SafeZoneDTO Empty constructor.
   */
  private SafeZoneDTO() {
    capacity = 100;
  }

  /**
   * SafeZoneDTO constructor.
   *
   * @param id       the id of the SafeZoneDTO
   * @param level    the amount of Resource at the start of the game
   * @param capacity the max of Resource the zone can hold.
   */
  private SafeZoneDTO(Long id, int level, int capacity) {
    this.id = id;
    this.level = level;
    this.capacity = capacity;
  }

  /**
   * Build default SafeZoneDTO.
   *
   * @return new SafeZoneDTO
   */
  public static SafeZoneDTO build() {
    return new SafeZoneDTO();
  }


  // CHECKSTYLE_OFF

  public Long getId() {
    return this.id;
  }

  public SafeZoneDTO setId(Long id) {
    this.id = id;
    return this;
  }

  public int getCapacity() {
    return this.capacity;
  }

  public SafeZoneDTO setCapacity(int capacity) {
    this.capacity = capacity;
    return this;
  }

  public int getLevel() {
    return this.level;
  }

  public SafeZoneDTO setLevel(int level) {
    this.level = level;
    return this;
  }

  public String getName() {
    return name;
  }

  public SafeZoneDTO setName(String name) {
    this.name = name;
    return this;
  }

  public Boolean isDestroyed() {
    return destroyed;
  }

  public SafeZoneDTO setDestroyed(Boolean destroyed) {
    this.destroyed = destroyed;
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

    SafeZoneDTO safeZoneDTO = (SafeZoneDTO) o;

    return level == safeZoneDTO.level && capacity == safeZoneDTO.capacity && (id != null ? id.equals(
            safeZoneDTO.id) : safeZoneDTO.id == null) && (name != null ? name.equals(safeZoneDTO.name) : safeZoneDTO.name == null);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + level;
    result = 31 * result + capacity;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
  // CHECKSTYLE_ON
}
