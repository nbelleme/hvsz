package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.SafeZoneService;
import io.nbelleme.hvsz.zone.internal.SafeZone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
final class SafeZoneServiceImpl implements SafeZoneService {

  private GameService gameService;

  /**
   * Constructor.
   *
   * @param gameService gameService
   */
  SafeZoneServiceImpl(GameService gameService) {
    this.gameService = Objects.requireNonNull(gameService);
  }

  @Override
  public SafeZone getSafeZone(long zoneId) {
    return null;
  }

  @Override
  public List<SafeZone> getSafeZones() {
    return null;
  }

  @Override
  public int refill(long zoneId, int token) {
    return 0;
  }

  @Override
  public void decreaseFoodLevel(Game game) {

  }

}
