package io.resourcepool.hvsz.common.exceptions;

import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.supply.FoodSupply;
import io.resourcepool.hvsz.zombies.ZombieZone;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 04/05/2017
 */
// CHECKSTYLE_OFF
public class HumanIsDeadException extends IllegalStateException {
  public SafeZone safeZone;
  public FoodSupply supplyZone;
  public ZombieZone zombieZone;

  public HumanIsDeadException(){}
  public HumanIsDeadException(Object zone) {
    if (zone instanceof SafeZone) safeZone = (SafeZone) zone;
    if (zone instanceof FoodSupply) supplyZone = (FoodSupply) zone;
    if (zone instanceof ZombieZone) zombieZone = (ZombieZone) zone;
  }
}
// CHECKSTYLE_ON