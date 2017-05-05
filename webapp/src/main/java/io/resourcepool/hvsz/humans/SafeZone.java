package io.resourcepool.hvsz.humans;

import java.io.Serializable;

public class SafeZone implements Serializable {
  private static final String NAME = "Zone de sécurité n°";
  private Long id;
  private int level;
  private int capacity;
  private String name;
  private Boolean destroyed = false;

  /**
   * SafeZone Empty constructor.
   */
  public SafeZone() {
  }

  /**
   * SafeZone constructor.
   *
   * @param id       the id of the SafeZone
   * @param level    the amount of Resource at the start of the game
   * @param capacity the max of Resource the zone can hold.
   */
  public SafeZone(Long id, int level, int capacity) {
    this.id = id;
    this.level = level;
    this.capacity = capacity;
    name = NAME + id;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * set resource level, set destroyed to true if level = 0.
   * @param level level to set
   */
  public void setLevel(int level) {
    this.level = level;
    if (level == 0) {
      destroyed = true;
    }
  }

  public int getCapacity() {
    return this.capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLevel() {
    return this.level;
  }

  public String getName() {
    return name;
  }

  public Boolean isDestroyed() {
    return destroyed;
  }

  public void setDestroyed(Boolean destroyed) {
    this.destroyed = destroyed;
  }

  /**
   * Refill an amount of food.
   *
   * @param amount the qty of food we want to refill in
   * @return how many level we have really refill
   */
  public int refill(int amount) {
    if (destroyed) {
      return 0;
    }
    int oldLevel = level;
    level = Math.min(oldLevel + amount, capacity);
    return level - oldLevel;
  }

  // CHECKSTYLE_OFF

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SafeZone safeZone = (SafeZone) o;

    if (level != safeZone.level) return false;
    if (capacity != safeZone.capacity) return false;
    if (id != null ? !id.equals(safeZone.id) : safeZone.id != null) return false;
    return name != null ? name.equals(safeZone.name) : safeZone.name == null;
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
