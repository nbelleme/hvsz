package io.resourcepool.hvsz.crons;

import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameStateEnum;
import io.resourcepool.hvsz.persistance.models.GameStatus;
import io.resourcepool.hvsz.service.GameService;
import io.resourcepool.hvsz.service.ResourceService;
import io.resourcepool.hvsz.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Handles the update of all running game's status.
 */
@Component
public class StatusUpdaterCron {

    @Autowired
    private GameService gameService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ResourceService resourceService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusUpdaterCron.class);

    private static final int SCHEDULED_TIME = 60000;

    /**
     * Schedules the update of all runing games.
     */
    @Scheduled(fixedRate = SCHEDULED_TIME)
    public void gameTimer() {
        List<Game> runningGames = gameService.getAllRuningGames();

        if (runningGames == null || runningGames.isEmpty()) {
            LOGGER.info("No running game to update.");
        } else {
            for (Game game : runningGames) {
                LOGGER.info("Update the game of id " + game.getId());
                GameStatus status = game.getStatus();
                if (status.getStarted() && status.getTimeLeft() > 0 && status.getGameState().equals(GameStateEnum.ONGOING.name())) {
                    status.setTimeLeft(status.getTimeLeft() - 1);
                    if (!game.checkSafeZoneLeft()) { // if no safezone has resource left, z victory
                        status.setGameState(GameStateEnum.ZOMBIE_VICTORY.name());
                    }
                    statusService.add(status, game.getId());
                } else if (status.getStarted() && status.getTimeLeft() <= 0 && status.getGameState().equals(GameStateEnum.ONGOING.name())) {
                    // End game, decide victory
                    status.setGameState(GameStateEnum.HUMAN_VICTORY.name());
                    statusService.add(status, game.getId());
                }

                //decrease resources, faster if difficulty higher.
                resourceService.decreaseSafezones(-(1 + game.getConfig().getDifficulty()));
            }

        }
    }

}
