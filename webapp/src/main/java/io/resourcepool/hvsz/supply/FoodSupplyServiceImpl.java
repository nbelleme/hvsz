package io.resourcepool.hvsz.supply;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.humans.Life;
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
    Game game = gameService.getActive();
    Assert.gameOngoing(game);
    return game.getFoodSupplies().stream().filter(foodSupply -> foodSupply.getId().equals(zoneId)).findFirst().get();
  }

  @Override
  public List<FoodSupply> getAll() {
    Game game = gameService.getActive();
    Assert.gameOngoing(game);
    return game.getFoodSupplies();
  }

  @Override
  public int takeFood(Long zoneId, int lifeToken, Integer amount) {
    Game game = gameService.getActive();
    Assert.gameActive(game);
    Life life = game.getStatus().getLifeByToken(lifeToken);
    Assert.humanAlive(life);

    FoodSupply foodSupply = get(zoneId);
    if (foodSupply == null) {
      throw new IllegalStateException("Cannot find the right food supply");
    }
    int result = foodSupply.pick(amount);
    gameService.update(game);
    return result;
  }
}
