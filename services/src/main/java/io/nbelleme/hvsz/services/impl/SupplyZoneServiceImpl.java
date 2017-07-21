package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.common.AssertGame;
import io.nbelleme.hvsz.common.AssertHuman;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.game.GameSettings;
import io.nbelleme.hvsz.humans.Human;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.SupplyZoneService;
import io.nbelleme.hvsz.zone.SupplyZone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.LongFunction;
import java.util.stream.LongStream;

/**
 * TODO class details.
 *
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
    Game game = gameService.getCurrent();
    AssertGame.gameOngoing(game);
    return game.getFoodSupplies()
               .stream()
               .filter(foodSupply -> foodSupply.getId().equals(zoneId))
               .findFirst()
               .orElse(null);
  }

  @Override
  public List<SupplyZone> getAll() {
    Game game = gameService.getCurrent();
    AssertGame.gameOngoing(game);
    return game.getFoodSupplies();
  }

  @Override
  public int takeFood(Long zoneId, int lifeToken, Integer amount) {
    Game game = gameService.getCurrent();
    AssertGame.gameActive(game);

    Human human = game.getStatus().getLifeByToken(lifeToken);

    SupplyZone supplyZone = game.getFoodSupplies()
                                .stream()
                                .filter(supply -> supply.getId().equals(zoneId))
                                .findFirst()
                                .orElse(null);

    if (supplyZone == null) {
      throw new IllegalStateException("Cannot find the right food supply");
    }

    AssertHuman.humanAlive(human, supplyZone);

    int originalResources = supplyZone.getLevel();
    int result = supplyZone.pick(amount);
    boolean humanFull = human.addResource(result);

    if (humanFull) { // if the human didn't have room for all resources, put excess back
      supplyZone.setLevel(originalResources - human.getNbResources());
    }

    gameService.save(game);
    return human.getNbResources();
  }

  @Override
  public List<SupplyZone> initFoodSupplies(GameSettings conf) {
    List<SupplyZone> foodSupplies = new ArrayList<>(conf.getNbFoodSupplyZones());
    int foodPerZone = conf.getNbFoodSupplies() / conf.getNbFoodSupplyZones();

    LongStream.range(0, conf.getNbFoodSupplyZones())
              .mapToObj(buildSupplyZone(foodPerZone))
              .forEach(foodSupplies::add);

    return foodSupplies;
  }

  /**
   * Build supplyzone.
   * @param foodPerZone parameter
   * @return lambda
   */
  private LongFunction<SupplyZone> buildSupplyZone(int foodPerZone) {
    return i -> SupplyZone.build()
                          .setId(i)
                          .setCapacity(foodPerZone)
                          .setLevel(foodPerZone);
  }
}
