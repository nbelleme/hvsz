package io.resourcepool.hvsz.controller.api;

import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameSettings;

public interface GameRestController {

  /**
   * Get the current game.
   *
   * @return Game
   */
  Game get();

  /**
   * Start a new game with default parameter.
   *
   * @return the game created
   */
  Game startDefault();

  /**
   * Start a new game using the GameSettings done.
   *
   * @param settings GameSettings
   * @return the game created
   */
  Game startGame(GameSettings settings);

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
