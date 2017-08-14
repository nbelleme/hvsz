package io.nbelleme.persistence.dao.api;

import io.nbelleme.hvsz.game.internal.Game;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by nbelleme on 02/08/2017.
 */
@Repository
public interface GameDao {

  /**
   * Find {@link Game} by id, wrapped in {@link Optional}.
   *
   * @param id of the item to find
   * @return Game from database
   */
  Optional<Game> findOne(String id);

  /**
   * Find {@link List} of all {@link Game} in database, wrapped in {@link Optional}.
   *
   * @return All games from database
   */
  Optional<List<Game>> findAll();

  /**
   * Insert {@link Game} in database, and return inserted object wrapped in {@link Optional}.
   *
   * @param game to insert
   * @return inserted {@link Game}
   */
  Optional<Game> insert(Game game);

  /**
   * Update {@link Game} in database.
   *
   * @param game game to update
   * @return Optional Game
   */
  Optional<Game> save(Game game);
}
