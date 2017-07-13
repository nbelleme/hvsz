package io.resourcepool.hvsz.controller.api;

import io.resourcepool.hvsz.supply.FoodSupply;

import java.util.List;

public interface FoodSupplyRestController {
  /**
   * Get a foodZone.
   *
   * @param zoneId the id of the foodSupply
   * @return the FoodSupply
   */
  FoodSupply getSupplyZone(Long zoneId);

  /**
   * Get all foodZone.
   *
   * @return List<FoodSupply>
   */
  List<FoodSupply> getAllSupplyZone();

  /**
   * Get if the safe-zone is empty or not.
   *
   * @param zoneId the id of the safe
   * @return true if empty, false else
   */
  boolean isEmpty(Long zoneId);
}
