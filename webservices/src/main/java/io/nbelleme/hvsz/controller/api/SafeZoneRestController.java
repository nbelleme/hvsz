package io.nbelleme.hvsz.controller.api;

import io.nbelleme.hvsz.zone.transfer.SafeZoneDTO;

import java.util.List;

public interface SafeZoneRestController {

  /**
   * Get a safe-zone.
   *
   * @param zoneId the id of the safe-zone
   * @return the safe-zone
   */
  SafeZoneDTO getSafeZone(Long zoneId);

  /**
   * Get all safe-zone.
   *
   * @return List<safe-zone>
   */
  List<SafeZoneDTO> getAllSafeZone();

  /**
   * Drop resource in zone.
   *
   * @param zoneId id of zone
   * @param lifeId the lifeId
   * @return the amount dropped
   */
  Integer dropResource(Long zoneId, int lifeId);

  /**
   * Get if the safe-zone is active or not.
   *
   * @param zoneId the id of the safe
   * @return true if active, false else
   */
  boolean isActive(Long zoneId);
}
