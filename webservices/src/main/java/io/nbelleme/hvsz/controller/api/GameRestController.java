package io.nbelleme.hvsz.controller.api;

import io.nbelleme.hvsz.game.internal.GameSettings;
import io.nbelleme.hvsz.game.transfer.GameDTO;

public interface GameRestController {

  /**
   * Get the current game.
   *
   * @return GameDTO
   */
  GameDTO get();

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
   */
  void pause();

  /**
   * Resume game.
   */
  void resume();

  /**
   * Stop the current game.
   */
  void stop();
}
