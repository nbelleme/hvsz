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
   */
  Game startGame();

  /**
   * Pause current game.
   *
   * @param id game id
   * @return optional
   */
  Optional<Game> pause(String id);

  /**
   * Resume current game.
   *
   * @param id game id
   * @return updated game
   */
  Optional<Game> resumeGame(String id);

  /**
   * Stop current game.
   *
   * @param id game id
   * @return game updated
   */
  Optional<Game> stopGame(String id);

  /**
   * Update game in database.
   *
   * @param game game to update
   * @return game updated
   */
  Optional<Game> update(Game game);


}
