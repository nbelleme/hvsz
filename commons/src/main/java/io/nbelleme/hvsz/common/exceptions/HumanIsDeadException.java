package io.nbelleme.hvsz.common.exceptions;

import io.nbelleme.hvsz.zone.internal.SafeZone;
import io.nbelleme.hvsz.zone.internal.SupplyZone;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 04/05/2017
 */

public class HumanIsDeadException extends IllegalStateException {
  private static final long serialVersionUID = -1222432595373111364L;
  private SafeZone safeZone;
  private SupplyZone supplyZone;

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
    if (zone instanceof SupplyZone) {
      supplyZone = (SupplyZone) zone;
    }
  }

  public SafeZone getSafeZone() {
    return safeZone;
  }

  public void setSafeZone(SafeZone safeZone) {
    this.safeZone = safeZone;
  }

  public SupplyZone getSupplyZone() {
    return supplyZone;
  }

  public void setSupplyZone(SupplyZone supplyZone) {
    this.supplyZone = supplyZone;
  }

}
// CHECKSTYLE_ON