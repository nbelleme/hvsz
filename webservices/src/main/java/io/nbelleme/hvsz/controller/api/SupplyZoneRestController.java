package io.nbelleme.hvsz.controller.api;

import io.nbelleme.hvsz.zone.transfer.SupplyZoneDTO;

import java.util.List;

public interface SupplyZoneRestController {
  /**
   * Get a foodZone.
   *
   * @param zoneId the id of the foodSupply
   * @return the FoodSupply
   */
  SupplyZoneDTO getSupplyZone(Long zoneId);

  /**
   * Get all foodZone.
   *
   * @return List<FoodSupply>
   */
  List<SupplyZoneDTO> getAllSupplyZone();

  /**
   * Get if the safe-zone is empty or not.
   *
   * @param zoneId the id of the safe
   * @return true if empty, false else
   */
  boolean isEmpty(Long zoneId);
}
