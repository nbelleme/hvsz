package io.nbelleme.hvsz.controller.rest.impl;

import io.nbelleme.hvsz.controller.rest.api.GameRestController;
import io.nbelleme.hvsz.game.Game;
import io.nbelleme.hvsz.game.GameSettings;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.GameSettingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/game")
public class GameRestControllerImpl implements GameRestController {

  private GameService gameService;

  private GameSettingsService gameSettingsService;

  /**
   * Constructor.
   *
   * @param gameService         the gameService
   * @param gameSettingsService the gameSettingsService
   */
  public GameRestControllerImpl(GameService gameService, GameSettingsService gameSettingsService) {
    this.gameService = Objects.requireNonNull(gameService);
    this.gameSettingsService = Objects.requireNonNull(gameSettingsService);
  }

  @Override
  @GetMapping()
  public Game get() {
    return gameService.get();
  }

  @Override
  @PostMapping("/start/default")
  public Game startDefault() {
    gameService.startGame();
    return gameService.get();
  }

  @Override
  @PostMapping("/start")
  public Game startGame(@RequestBody GameSettings settings) {
    gameSettingsService.set(settings);
    gameService.startGame();
    return gameService.get();
  }

  @Override
  @PostMapping("/pause")
  public void pause() {
    gameService.pauseGame();
  }

  @Override
  @PostMapping("/resume")
  public void resume() {
    gameService.resumeGame();
  }

  @Override
  @PostMapping("/stop")
  public void stop() {
    gameService.stopGame();
  }
}
