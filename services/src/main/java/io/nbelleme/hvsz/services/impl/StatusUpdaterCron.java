package io.nbelleme.hvsz.services.impl;

import io.nbelleme.hvsz.common.exceptions.NoGameDefinedException;
import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.internal.Status;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.SafeZoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


/**
 * This takes care of updating the time-related aspects of a game (time remaining + resource decrease).
 */
@Component
public class StatusUpdaterCron {

  private static final Logger LOGGER = LoggerFactory.getLogger(StatusUpdaterCron.class);
  private static final int SCHEDULED_TIME = 1000;
  private static final int MILLIS_IN_SECOND = 1000;

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
    List<Game> games = gameService.getAll()
                                  .orElseThrow(NoGameDefinedException::new);

    games.forEach(this::updateGame);
  }

  /**
   * update game.
   *
   * @param game game to update.
   */
  private void updateGame(Game game) {
    if (game != null && game.getStatus().isActive()) {

      Status status = game.getStatus();

      if (status.isActive() && status.getRemainingTime() > 0) {
        status.setRemainingTime(status.getRemainingTime() - SCHEDULED_TIME / MILLIS_IN_SECOND);
      }

      safeZoneService.decreaseFoodLevel(game);
      game.setStatus(status);
      gameService.update(game)
                 .orElseThrow(NoGameDefinedException::new);

//      LOGGER.info("[ CRON ] : Update the game of id " + gameUpdated.getId());
    }
  }
}
