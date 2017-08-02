package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.controller.api.GameRestController;
import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.internal.GameSettings;
import io.nbelleme.hvsz.game.mapper.GameMapper;
import io.nbelleme.hvsz.game.transfer.GameDTO;
import io.nbelleme.hvsz.services.api.GameService;
import io.nbelleme.hvsz.services.api.GameSettingsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
  @GetMapping("/get/one/{id}")
  public GameDTO get(@PathVariable("id") String id) {
    return gameService.get(id)
                      .map(GameMapper::toDTO)
                      .orElse(null);
  }

  @Override
  @GetMapping("/get/all")
  public List<GameDTO> getAll() {
    return gameService.getAll()
                      .map(GameMapper::toDTO)
                      .orElse(null);
  }

  @Override
  @PostMapping("/start/default")
  public GameDTO startDefault() {
    gameService.startGame();
    return GameMapper.toDTO(Game.build());
  }

  @Override
  @PostMapping("/start")
  public GameDTO startGame(@RequestBody GameSettings settings) {
    return null;
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
