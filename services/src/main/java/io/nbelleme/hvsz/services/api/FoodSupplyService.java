package io.nbelleme.hvsz.services.api;

import io.nbelleme.hvsz.supply.FoodSupply;

import java.util.List;

public interface FoodSupplyService {

  /**
   * @param zoneId the zone id
   * @return the food supply zone associated with the zoneId.
   */
  FoodSupply get(Long zoneId);

  /**
   * @return the list of food supply zones
   */
  List<FoodSupply> getAll();

  /**
   * Take some food out of a supply zone.
   *
   * @param zoneId    the zone id
   * @param lifeToken the life token
   * @param amount    the quantity to take in kgs
   * @return the amount of food taken
   */
  int takeFood(Long zoneId, int lifeToken, Integer amount);


}
