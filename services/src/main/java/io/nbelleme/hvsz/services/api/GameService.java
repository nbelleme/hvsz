package io.nbelleme.hvsz.services.api;

import io.nbelleme.hvsz.game.internal.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

  /**
   * Return game by id.
   *
   * @param id to retreive
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
   * @return game started
   * @throws io.nbelleme.hvsz.common.exceptions.IllegalGameStateException if game is already in progress.
   */
  Game startGame();

  /**
   * Pause current game.
   *
   * @param id game id
   * @return optional
   * @throws io.nbelleme.hvsz.common.exceptions.NoGameDefinedException if no game is currently being played
   */
  Optional<Game> pause(String id);

  /**
   * Resume current game.
   *
   * @param id game id
   * @return updated game
   * @throws io.nbelleme.hvsz.common.exceptions.NoGameDefinedException if no game is currently being played
   */
  Optional<Game> resumeGame(String id);

  /**
   * Stop current game.
   *
   * @throws io.nbelleme.hvsz.common.exceptions.NoGameDefinedException if no game is currently being played
   */
  void stopGame();

  /**
   * Update game in database.
   *
   * @param game game to update
   * @return game updated
   */
  Optional<Game> update(Game game);


}
