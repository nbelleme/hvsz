package io.nbelleme.hvsz.zone;


import java.io.Serializable;

public class FoodSupply implements Serializable {

  private static final long serialVersionUID = 4499800033671757363L;

  private static final String NAME = "Zone de ravitaillement n°";

  private Long id;
  private int level;
  private int capacity;
  private String name;

  /**
   * SupplyZone Empty constructor.
   */
  private FoodSupply() {
  }

  /**
   * SupplyZone constructor.
   *
   * @param id       the id of the SupplyZone
   * @param level    the current amount of resource
   * @param capacity the maximum amount of Resource (<=> available at the start of the game)
   */
  private FoodSupply(Long id, int level, int capacity) {
    this.id = id;
    this.level = level;
    this.capacity = capacity;
    name = NAME + id;
  }

  /**
   * Build default FoodSupply.
   *
   * @return default FoodSupply
   */
  public static FoodSupply build() {
    return new FoodSupply();
  }


  //CHECKSTYLE_OFF
  public Long getId() {
    return this.id;
  }

  public FoodSupply setId(Long id) {
    this.id = id;
    return this;
  }

  public int getCapacity() {
    return capacity;
  }

  public FoodSupply setCapacity(int capacity) {
    this.capacity = capacity;
    return this;
  }

  public int getLevel() {
    return level;
  }

  public FoodSupply setLevel(int level) {
    this.level = level;
    return this;
  }

  public String getName() {
    return name;
  }

  public FoodSupply setName(String name) {
    this.name = name;
    return this;
  }

  //CHECKSTYLE_ON

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
