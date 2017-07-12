package io.resourcepool.hvsz.common.exceptions;

import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.supply.FoodSupply;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 04/05/2017
 */

public class HumanIsDeadException extends IllegalStateException {
  private SafeZone safeZone;
  private FoodSupply supplyZone;

  /**
   * Constructor.
   */
  public HumanIsDeadException() {
  }

  /**
   * Constructor with zone.
   *
   * @param zone zone
   */
  public HumanIsDeadException(Object zone) {
    if (zone instanceof SafeZone) {
      safeZone = (SafeZone) zone;
    }
    if (zone instanceof FoodSupply) {
      supplyZone = (FoodSupply) zone;
    }
  }

  public SafeZone getSafeZone() {
    return safeZone;
  }

  public void setSafeZone(SafeZone safeZone) {
    this.safeZone = safeZone;
  }

  public FoodSupply getSupplyZone() {
    return supplyZone;
  }

  public void setSupplyZone(FoodSupply supplyZone) {
    this.supplyZone = supplyZone;
  }

}
// CHECKSTYLE_ON