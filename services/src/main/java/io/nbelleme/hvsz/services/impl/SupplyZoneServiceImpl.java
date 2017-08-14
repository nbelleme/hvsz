package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.game.internal.GameSettings;
import io.nbelleme.hvsz.services.api.SupplyZoneService;
import io.nbelleme.hvsz.zone.internal.SupplyZone;
import io.nbelleme.persistence.dao.api.SupplyZoneDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Loïc Ortola on 04/05/2017
 */
@Service
final class SupplyZoneServiceImpl implements SupplyZoneService {

  private SupplyZoneDao dao;

  /**
   * Constructor.
   *
   * @param dao SupplyZoneDao
   */
  SupplyZoneServiceImpl(SupplyZoneDao dao) {
    this.dao = Objects.requireNonNull(dao);
  }

  @Override
  public SupplyZone get(Long zoneId) {
    return null;
  }

  @Override
  public List<SupplyZone> getAll() {
    return null;
  }

  @Override
  public int takeFood(Long zoneId, int lifeToken, Integer amount) {
    return 0;
  }

  @Override
  public List<SupplyZone> initSupplyZones(GameSettings conf) {
    List<SupplyZone> supplyZones = new ArrayList<>();
    int foodPerZone = conf.getNbFoodSupplies() / conf.getNgSupplyZones();

    SupplyZone supplyZone = SupplyZone.build()
                                      .setLevel(foodPerZone)
                                      .setCapacity(foodPerZone);

    Optional<SupplyZone> supplyZoneOptional = dao.insert(supplyZone);

    supplyZones.add(supplyZoneOptional.orElse(null));
    return supplyZones;
  }
}
