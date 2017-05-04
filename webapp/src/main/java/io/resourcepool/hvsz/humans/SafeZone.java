package io.resourcepool.hvsz.humans;

import java.io.Serializable;

public class SafeZone implements Serializable {
  private static final String NAME = "Zone de sécurité n°";
  private Integer id;
  private int level;
  private int capacity;
  private String name;

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
  public SafeZone(int id, int level, int capacity) {
    this.id = id;
    this.level = level;
    this.capacity = capacity;
    name = NAME + id;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setLevel(int level) {
    this.level = level;
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
    return level;
  }

  public String getName() {
    return name;
  }

  /**
   * Refill an amount of food.
   *
   * @param amount the qty of food we want to refill in
   * @return how many level we have really refill
   */
  public int refill(int amount) {
    int oldLevel = level;
    level = Math.min(oldLevel + amount, capacity);
    return level - oldLevel;
  }

  /**
   * Lose an amount of level (refill out).
   *
   * @param quantite amount of level we want to refill out
   * @return how many level we have really refill
   */
  public int lose(int quantite) {
    if (quantite <= 0) {
      return 0;
    }
    level -= quantite;
    if (level < 0) {
      int over = level;
      level = 0;
      return quantite - over;
    }
    return quantite;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SafeZone safeZone = (SafeZone) o;
    return level == safeZone.level && capacity == safeZone.capacity && (id != null ? id.equals(safeZone.id) : safeZone.id == null) && (name != null ? name.equals(safeZone.name) : safeZone.name == null);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + level;
    result = 31 * result + capacity;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
