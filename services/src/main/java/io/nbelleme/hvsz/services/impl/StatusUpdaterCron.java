package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.game.Status;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.SafeZoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;


/**
 * This takes care of updating the time-related aspects of a game (time remaining + resource decrease).
 */
@Component
public class StatusUpdaterCron {

  private static final Logger LOGGER = LoggerFactory.getLogger(StatusUpdaterCron.class);
  private static final int SCHEDULED_TIME = 1000;

  private GameService gameService;
  private SafeZoneService safeZoneService;

  /**
   * Constructor.
   *
   * @param gameService     gameService
   * @param safeZoneService safeZoneService
   */
  public StatusUpdaterCron(GameService gameService, SafeZoneService safeZoneService) {
    this.gameService = Objects.requireNonNull(gameService);
    this.safeZoneService = Objects.requireNonNull(safeZoneService);
  }

  /**
   * Schedules the update of all running games.
   */
  @Scheduled(fixedRate = SCHEDULED_TIME)
  public void gameTimer() {
    Game game = gameService.getCurrent();
    if (game == null || !game.getStatus().isOngoing()) {
      LOGGER.info("[ CRON ] : No running game to update.");
    } else {

      Status status = game.getStatus();

      if (status.isActive() && status.getRemainingTime() > 0) {
        status.setRemainingTime(status.getRemainingTime() - SCHEDULED_TIME/1000);
      }

      if (shouldDecreaseLevel(100, (long) (game.getConfig().getGameDuration() * 60), status.getRemainingTime(), game.getConfig().getDifficulty())) {
        safeZoneService.eatOneUnitOfFood();
      }
      game = gameService.getCurrent();
      Game gameUpdated = gameService.save(game);
      LOGGER.info("[ CRON ] : Update the game of id " + gameUpdated.getId());
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
    long timeToHunger = (long) (0.66 * totalTime);

    if (difficulty == 1) {
      timeToHunger = (long) (0.66 * totalTime);

    } else if (difficulty == 2) {
      timeToHunger = (long) (0.5 * totalTime);

    } else if (difficulty == 3) {
      timeToHunger = (long) (0.33 * totalTime);
    }

    Long timeToDecrementUnit = timeToHunger / totalFoodUnits;
    return remainingTime % timeToDecrementUnit == 0;
  }

}
