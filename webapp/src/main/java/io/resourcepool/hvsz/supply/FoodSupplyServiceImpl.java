package io.resourcepool.hvsz.supply;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.humans.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 04/05/2017
 */
@Service
public class FoodSupplyServiceImpl implements FoodSupplyService {

  @Autowired
  private GameService gameService;

  @Override
  public FoodSupply get(Long zoneId) {
    Game game = gameService.get();
    Assert.gameOngoing(game);
    return game.getFoodSupplies().stream().filter(foodSupply -> foodSupply.getId().equals(zoneId)).findFirst().get();
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
    Human human = game.getStatus().getLifeByToken(lifeToken);
    Assert.humanAlive(human);

    FoodSupply foodSupply = game.getFoodSupplies().stream().filter(supply -> supply.getId().equals(zoneId)).findFirst().get();
    if (foodSupply == null) {
      throw new IllegalStateException("Cannot find the right food supply");
    }
    int originalResources = foodSupply.getLevel();
    int result = foodSupply.pick(amount);
    int humanResult = human.addResource(result);
    if (humanResult != result) { // if the human didn't have room for all resources, put excess back
      foodSupply.setLevel(originalResources - humanResult);
    }
    gameService.update(game);
    return humanResult;
  }
}
