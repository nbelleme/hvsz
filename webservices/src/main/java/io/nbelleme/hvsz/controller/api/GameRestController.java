package io.nbelleme.hvsz.controller.api;

import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.hvsz.game.internal.GameSettings;
import io.nbelleme.hvsz.game.transfer.GameDTO;

import java.util.List;
import java.util.Optional;

public interface GameRestController {

  /**
   * Get game by id.
   *
   * @param id of the game to get
   * @return GameDTO
   */
  GameDTO get(String id);

  /**
   * List all games in database.
   *
   * @return list of game.
   */
  List<GameDTO> getAll();

  /**
   * Start a new game with default parameter.
   *
   * @return the game created
   */
  GameDTO startDefault();

  /**
   * Start a new game using the GameSettings done.
   *
   * @param settings GameSettings
   * @return the game created
   */
  GameDTO startGame(GameSettings settings);

  /**
   * Put the game on pause.
   *
   * @param id game id
   *           @return updated game
   */
  GameDTO pause(String id);

  /**
   * Resume game.
   */
  void resume();

  /**
   * Stop the current game.
   */
  void stop();
}
