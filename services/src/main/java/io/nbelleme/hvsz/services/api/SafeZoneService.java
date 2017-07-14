package io.nbelleme.hvsz.services.api;

import io.nbelleme.hvsz.zone.SafeZone;

import java.util.List;

public interface SafeZoneService {

  /**
   * Get the correponding safe zone.
   *
   * @param zoneId the id of the safe zone
   * @return the SafeZone
   */
  SafeZone getSafeZone(Long zoneId);

  /**
   * Return all the Safe Zone.
   *
   * @return a list of safe zone
   */
  List<SafeZone> getSafeZones();

  /**
   * * Offload transported food into the safe zone.
   *
   * @param zoneId the safe zone id where we will refill the food.
   * @param token  the human token
   * @return the amount which was offloaded
   */
  int refill(Long zoneId, int token);

  /**
   * This method will decrement the units of food by 1 in each safe zone.
   *
   * @see io.nbelleme.hvsz.game.StatusUpdaterCron for more details.
   */
  void eatOneUnitOfFood();
}
