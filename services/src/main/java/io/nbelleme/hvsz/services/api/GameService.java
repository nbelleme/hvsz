package io.nbelleme.hvsz.services.api;

import io.nbelleme.hvsz.game.internal.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

  /**
   * Return game by id.
   *
   * @return the active game or null if none found.
   */
  Optional<Game> get(String id);

  /**
   * Return all games.
   *
   * @return all games.
   */
  Optional<List<Game>> getAll();

  /**
   * Start game using the game settings previously saved.
   *
   * @throws io.nbelleme.hvsz.common.exceptions.IllegalGameStateException if game is already in progress.
   */
  void startGame();

  /**
   * Pause current game.
   *
   * @throws io.nbelleme.hvsz.common.exceptions.NoGameDefinedException if no game is currently being played
   */
  void pauseGame();

  /**
   * Resume current game.
   *
   * @throws io.nbelleme.hvsz.common.exceptions.NoGameDefinedException if no game is currently being played
   */
  void resumeGame();

  /**
   * Stop current game.
   *
   * @throws io.nbelleme.hvsz.common.exceptions.NoGameDefinedException if no game is currently being played
   */
  void stopGame();

  /**
   * Update game.
   * Will check the current status and update it accordingly (detects end of game for instance)
   *
   * @param g the game to update
   * @return game game inserted
   */
  Optional<Game> save(Game g);
}
