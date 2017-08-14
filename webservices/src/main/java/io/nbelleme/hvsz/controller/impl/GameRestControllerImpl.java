package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.common.exceptions.NoGameDefinedException;
import io.nbelleme.hvsz.controller.api.GameRestController;
import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.internal.GameSettings;
import io.nbelleme.hvsz.game.transfer.GameDTO;
import io.nbelleme.hvsz.mapper.api.GenericMapper;
import io.nbelleme.hvsz.services.api.GameService;
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

  //TODO check exceptions

  private GameService gameService;

  private GenericMapper<Game, GameDTO> gameMapper;

  /**
   * Constructor.
   *
   * @param gameService the gameService
   * @param gameMapper  gameMapper
   */
  public GameRestControllerImpl(GameService gameService, GenericMapper<Game, GameDTO> gameMapper) {
    this.gameService = Objects.requireNonNull(gameService);
    this.gameMapper = Objects.requireNonNull(gameMapper);
  }

  @Override
  @GetMapping("/get/{id}")
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
    Game game = gameService.startGame();
    return gameMapper.toDTO(game);
  }

  @Override
  @PostMapping("/start")
  public GameDTO startGame(@RequestBody GameSettings settings) {
    return null;
  }

  @Override
  @PostMapping("/{id}/pause")
  public GameDTO pause(@PathVariable("id") String id) {
    return gameService.pause(id)
                      .map(gameMapper::toDTO)
                      .orElseThrow(NoGameDefinedException::new);
  }

  @Override
  @PostMapping("/{id}/resume")
  public GameDTO resume(@PathVariable("id") String id) {
    return gameService.resumeGame(id)
                      .map(gameMapper::toDTO)
                      .orElseThrow(NoGameDefinedException::new);
  }

  @Override
  @PostMapping("/{id}//stop")
  public GameDTO stop(@PathVariable("id") String id) {
    return gameService.stopGame(id).map(gameMapper::toDTO)
                   .orElseThrow(NoGameDefinedException::new);
  }
}
