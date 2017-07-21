package io.nbelleme.hvsz.services.api;

import io.nbelleme.hvsz.game.GameSettings;
import io.nbelleme.hvsz.zone.SupplyZone;

import java.util.List;

public interface SupplyZoneService {

  /**
   * @param zoneId the zone id
   * @return the food supply zone associated with the zoneId.
   */
  SupplyZone get(Long zoneId);

  /**
   * @return the list of food supply zones
   */
  List<SupplyZone> getAll();

  /**
   * Take some food out of a supply zone.
   *
   * @param zoneId    the zone id
   * @param lifeToken the life token
   * @param amount    the quantity to take in kgs
   * @return the amount of food taken
   */
  int takeFood(Long zoneId, int lifeToken, Integer amount);

  /**
   * Init array of supplyzones.
   * @param gameSettings gameSettings to init supplyzones
   * @return List of supplyzones
   */
  List<SupplyZone> initFoodSupplies(GameSettings gameSettings);

}
