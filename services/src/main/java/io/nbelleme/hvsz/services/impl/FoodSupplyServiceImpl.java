package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.common.Assert;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.humans.Life;
import io.nbelleme.hvsz.services.api.FoodSupplyService;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.supply.FoodSupply;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 04/05/2017
 */
@Service
final class FoodSupplyServiceImpl implements FoodSupplyService {

  private GameService gameService;

  /**
   * Constructor.
   *
   * @param gameService gameService
   */
  FoodSupplyServiceImpl(GameService gameService) {
    this.gameService = Objects.requireNonNull(gameService);
  }

  @Override
  public FoodSupply get(Long zoneId) {
    Game game = gameService.get();
    Assert.gameOngoing(game);
    return game.getFoodSupplies().stream().filter(foodSupply -> foodSupply.getId().equals(zoneId)).findFirst().orElse(null);
  }

  @Override
  public List<FoodSupply> getAll() {
    Game game = gameService.get();
    Assert.gameOngoing(game);
    return game.getFoodSupplies();
  }

  @Override
  public int takeFood(Long zoneId, int lifeToken, Integer amount) {
    Game game = gameService.get();
    Assert.gameActive(game);
    Life life = game.getStatus().getLifeByToken(lifeToken);
    FoodSupply foodSupply = game.getFoodSupplies().stream().filter(supply -> supply.getId().equals(zoneId)).findFirst().orElse(null);
    if (foodSupply == null) {
      throw new IllegalStateException("Cannot find the right food supply");
    }

    Assert.humanAlive(life, foodSupply);

    int originalResources = foodSupply.getLevel();
    int result = foodSupply.pick(amount);
    int humanResult = life.addResource(result);
    if (humanResult != result) { // if the human didn't have room for all resources, put excess back
      foodSupply.setLevel(originalResources - humanResult);
    }
    gameService.update(game);
    return humanResult;
  }
}
