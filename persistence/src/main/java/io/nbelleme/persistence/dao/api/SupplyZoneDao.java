package io.nbelleme.persistence.dao.api;

import io.nbelleme.hvsz.zone.internal.SupplyZone;

import java.util.Optional;

public interface SupplyZoneDao {

  /**
   * Update existing object.
   *
   * @param supplyZone supplyZone
   * @return optional object updated
   */
  Optional<SupplyZone> update(SupplyZone supplyZone);

  /**
   * Insert existing object.
   *
   * @param supplyZone supplyZone
   * @return optional object inserted
   */
  Optional<SupplyZone> insert(SupplyZone supplyZone);

}
