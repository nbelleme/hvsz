package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.common.exceptions.NoGameDefinedException;
import io.nbelleme.hvsz.controller.api.GameRestController;
import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.internal.GameSettings;
import io.nbelleme.hvsz.game.transfer.GameDTO;
import io.nbelleme.hvsz.mapper.api.GenericMapper;
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

  private GenericMapper<Game, GameDTO> gameMapper;

  /**
   * Constructor.
   *
   * @param gameService         the gameService
   * @param gameSettingsService the gameSettingsService
   * @param gameMapper          gameMapper
   */
  public GameRestControllerImpl(GameService gameService, GameSettingsService gameSettingsService, GenericMapper<Game, GameDTO> gameMapper) {
    this.gameService = Objects.requireNonNull(gameService);
    this.gameSettingsService = Objects.requireNonNull(gameSettingsService);
    this.gameMapper = Objects.requireNonNull(gameMapper);
  }

  @Override
  @GetMapping("/get/one/{id}")
  public GameDTO get(@PathVariable("id") String id) {
    return gameService.get(id)
                      .map(gameMapper::toDTO)
                      .orElseThrow(NoGameDefinedException::new);
  }

  @Override
  @GetMapping("/get/all")
  public List<GameDTO> getAll() {
    return gameService.getAll()
                      .map(gameMapper::toDTO)
                      .orElseThrow(NoGameDefinedException::new);
  }

  @Override
  @PostMapping("/start/default")
  public GameDTO startDefault() {
    gameService.startGame();
    return gameMapper.toDTO(Game.build());
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
