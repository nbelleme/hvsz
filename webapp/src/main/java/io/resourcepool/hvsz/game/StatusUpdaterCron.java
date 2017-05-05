package io.resourcepool.hvsz.game;

import io.resourcepool.hvsz.humans.SafeZoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * This takes care of updating the time-related aspects of a game (time remaining + resource decrease).
 */
@Component
public class StatusUpdaterCron {

  @Autowired
  private GameService gameService;

  @Autowired
  private SafeZoneService safeZoneService;

  private static final Logger LOGGER = LoggerFactory.getLogger(StatusUpdaterCron.class);

  private static final int SCHEDULED_TIME = 1000;

  /**
   * Schedules the update of all running games.
   */
  @Scheduled(fixedRate = SCHEDULED_TIME)
  public void gameTimer() {
    Game game = gameService.get();
    if (game == null || !game.getStatus().isOngoing()) {
      LOGGER.debug("No running game to update.");
    } else {

      LOGGER.info("Update the game of id " + game.getId());
      Status status = game.getStatus();
      if (status.isActive() && status.getRemainingTime() > 0) {
        status.setRemainingTime(status.getRemainingTime() - 1);
        gameService.update(game);
      }
      if (shouldDecreaseLevel(100, (long) (game.getConfig().getGameDuration() * 60), status.getRemainingTime(), game.getConfig().getDifficulty())) {
        safeZoneService.eatOneUnitOfFood();
      }
      game = gameService.get();
      gameService.update(game);
    }
  }

  /**
   * This method checks whether the cron should trigger the decrease of one unit of food.
   * This is computed in function of time-related game settings.
   *
   * @param totalFoodUnits .
   * @param totalTime      .
   * @param remainingTime  .
   * @param difficulty     .
   * @return true if should decrease, false otherwise
   */
  private boolean shouldDecreaseLevel(int totalFoodUnits, Long totalTime, Long remainingTime, int difficulty) {
    // Calculate the number of seconds necessary before taking one unit of food.
    Long timeToHunger = 0L;
    switch (difficulty) {
      case 1: // EASY <=> Food gets eaten in 2/3 time
        timeToHunger = (long) (0.66 * totalTime);
        break;
      case 2: // MEDIUM <=> Food gets eaten in 1/2 time
        timeToHunger = (long) (0.5 * totalTime);
        break;
      case 3: // HARD <=> Food gets eaten in 1/3 time
        timeToHunger = (long) (0.33 * totalTime);
        break;
    }
    Long timeToDecrementUnit = timeToHunger / totalFoodUnits;
    return remainingTime % timeToDecrementUnit == 0;
  }

}
