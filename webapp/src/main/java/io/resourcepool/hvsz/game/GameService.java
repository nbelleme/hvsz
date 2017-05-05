package io.resourcepool.hvsz.game;

public interface GameService {

  /**
   * @return the active game or null if none found.
   */
  Game get();

  /**
   * Start game using the game settings previously saved.
   *
   * @throws io.resourcepool.hvsz.common.exceptions.IllegalGameStateException if game is already in progress.
   */
  void startGame();

  /**
   * Pause current game.
   *
   * @throws io.resourcepool.hvsz.common.exceptions.NoGameDefinedException if no game is currently being played
   */
  void pauseGame();

  /**
   * Resume current game.
   *
   * @throws io.resourcepool.hvsz.common.exceptions.NoGameDefinedException if no game is currently being played
   */
  void resumeGame();

  /**
   * Stop current game.
   *
   * @throws io.resourcepool.hvsz.common.exceptions.NoGameDefinedException if no game is currently being played
   */
  void stopGame();

  /**
   * Update game.
   * Will check the current status and update it accordingly (detects end of game for instance)
   *
   * @param g the game to update
   */
  void update(Game g);
}
