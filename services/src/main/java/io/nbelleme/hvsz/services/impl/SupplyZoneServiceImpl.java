package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.SupplyZoneService;
import io.nbelleme.hvsz.zone.internal.SupplyZone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author Loïc Ortola on 04/05/2017
 */
@Service
final class SupplyZoneServiceImpl implements SupplyZoneService {

  private GameService gameService;

  /**
   * Constructor.
   *
   * @param gameService gameService
   */
  SupplyZoneServiceImpl(GameService gameService) {
    this.gameService = Objects.requireNonNull(gameService);
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

}
