package io.resourcepool.hvsz.supply;


import java.io.Serializable;

public class FoodSupply implements Serializable {
  private static final String NAME = "Zone de ravitaillement n°";
  private Long id;
  private int level;
  private int capacity;
  private String name;

  /**
   * SupplyZone Empty constructor.
   */
  public FoodSupply() {
  }

  /**
   * SupplyZone constructor.
   *
   * @param id       the id of the SupplyZone
   * @param level    the current amount of resource
   * @param capacity the maximum amount of Resource (<=> available at the start of the game)
   */
  public FoodSupply(Long id, int level, int capacity) {
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

  public int getCapacity() {
    return capacity;
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
   * Take away an amount of food.
   *
   * @param amount amount of food we want to take out
   * @return int (return the successful gotten amount)
   */
  public int pick(int amount) {
    int oldLevel = level;
    level -= Math.min(level, amount);
    return amount > oldLevel ? oldLevel : amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    FoodSupply that = (FoodSupply) o;

    return capacity == that.capacity && (id != null ? id.equals(that.id) : that.id == null) && (name != null ? name.equals(that.name) : that.name == null);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + capacity;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
