package io.resourcepool.hvsz.services.impl;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.services.api.FoodSupplyService;
import io.resourcepool.hvsz.services.api.GameService;
import io.resourcepool.hvsz.humans.Life;
import io.resourcepool.hvsz.supply.FoodSupply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 04/05/2017
 */
@Service
public class FoodSupplyServiceImpl implements FoodSupplyService {

  private GameService gameService;

  public FoodSupplyServiceImpl(GameService gameService) {
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
