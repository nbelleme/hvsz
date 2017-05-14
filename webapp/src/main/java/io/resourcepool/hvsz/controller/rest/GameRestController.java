package io.resourcepool.hvsz.controller.rest;

import io.resourcepool.hvsz.common.models.GenericBuilder;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.game.GameSettings;
import io.resourcepool.hvsz.game.GameSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gdanguy on 05/05/17.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/game")
public class GameRestController {
    private static final int DEFAULT_GAME_DURATION = 30;
    private static final int DEFAULT_DIFFICULTY = 1;
    private static final int DEFAULT_HUMAN_TICKETS = 100;
    private static final int DEFAULT_MAX_HUMAN_ON_FIELD = 30;
    private static final int DEFAULT_NB_SAFE_ZONE = 2;
    private static final int DEFAULT_NB_FOOD_ZONE = 2;
    private static final int DEFAULT_FOOD_SUPPLIES = 180;
    private static final int DEFAULT_MAX_FOOD_TRANSFERT = 1;
    private static final int DEFAULT_SAFE_ZONE_DROP_RATE = 30;
    private static final GameSettings DEFAULT_SETTINGS =
            GenericBuilder.of(GameSettings::new)
                    .with(GameSettings::setGameDuration, DEFAULT_GAME_DURATION)
                    .with(GameSettings::setDifficulty, DEFAULT_DIFFICULTY)
                    .with(GameSettings::setHumanTickets, DEFAULT_HUMAN_TICKETS)
                    .with(GameSettings::setMaxHumansOnField, DEFAULT_MAX_HUMAN_ON_FIELD)
                    .with(GameSettings::setNbSafeZones, DEFAULT_NB_SAFE_ZONE)
                    .with(GameSettings::setNbFoodSupplyZones, DEFAULT_NB_FOOD_ZONE)
                    .with(GameSettings::setNbFoodSupplies, DEFAULT_FOOD_SUPPLIES)
                    .with(GameSettings::setMaximumFoodTransfer, DEFAULT_MAX_FOOD_TRANSFERT)
                    .with(GameSettings::setSafezoneDropRate, DEFAULT_SAFE_ZONE_DROP_RATE)
                    .build();

    @Autowired
    private GameService gameService;

    @Autowired
    private GameSettingsService gameSettingsService;

    /**
     * Get the current game.
     *
     * @return Game
     */
    @GetMapping()
    public ResponseEntity<Game> get() {

        Game game = gameService.get();
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    /**
     * Start a new game with default parameter.
     *
     * @return the game created
     */
    @PostMapping("/start/default")
    public Game startDefault() {
        gameSettingsService.set(DEFAULT_SETTINGS);
        gameService.startGame();
        return gameService.get();
    }

    /**
     * Start a new game using the GameSettings done.
     *
     * @param settings GameSettings
     * @return the game created
     */
    @PostMapping("/start")
    public Game startGame(@RequestBody GameSettings settings) {
        gameSettingsService.set(settings);
        gameService.startGame();
        return gameService.get();
    }

    /**
     * Put the game on pause.
     */
    @PostMapping("/pause")
    public void pause() {
        gameService.pauseGame();
    }

    /**
     * Resume game.
     */
    @PostMapping("/resume")
    public void resume() {
        gameService.resumeGame();
    }

    /**
     * Stop the current game.
     */
    @PostMapping("/stop")
    public void stop() {
        gameService.stopGame();
    }
}
