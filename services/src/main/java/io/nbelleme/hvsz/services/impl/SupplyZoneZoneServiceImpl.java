package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.common.Assert;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.humans.Life;
import io.nbelleme.hvsz.services.api.SupplyZoneService;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.zone.SupplyZone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 04/05/2017
 */
@Service
final class SupplyZoneZoneServiceImpl implements SupplyZoneService {

  private GameService gameService;

  /**
   * Constructor.
   *
   * @param gameService gameService
   */
  SupplyZoneZoneServiceImpl(GameService gameService) {
    this.gameService = Objects.requireNonNull(gameService);
  }

  @Override
  public SupplyZone get(Long zoneId) {
    Game game = gameService.getCurrent();
    Assert.gameOngoing(game);
    return game.getFoodSupplies().stream().filter(foodSupply -> foodSupply.getId().equals(zoneId)).findFirst().orElse(null);
  }

  @Override
  public List<SupplyZone> getAll() {
    Game game = gameService.getCurrent();
    Assert.gameOngoing(game);
    return game.getFoodSupplies();
  }

  @Override
  public int takeFood(Long zoneId, int lifeToken, Integer amount) {
    Game game = gameService.getCurrent();
    Assert.gameActive(game);

    Life life = game.getStatus().getLifeByToken(lifeToken);

    SupplyZone supplyZone = game.getFoodSupplies()
        .stream()
        .filter(supply -> supply.getId().equals(zoneId))
        .findFirst()
        .orElse(null);

    if (supplyZone == null) {
      throw new IllegalStateException("Cannot find the right food supply");
    }

    Assert.humanAlive(life, supplyZone);

    int originalResources = supplyZone.getLevel();
    int result = supplyZone.pick(amount);
    boolean humanFull = life.addResource(result);

    if (humanFull) { // if the human didn't have room for all resources, put excess back
      supplyZone.setLevel(originalResources - life.getNbResources());
    }

    gameService.update(game);
    return life.getNbResources();
  }
}