package io.nbelleme.persistence.dao.impl;

import io.nbelleme.hvsz.game.Game;
import io.nbelleme.persistence.dao.api.GameDao;
import io.nbelleme.persistence.dpo.impl.GameDpo;
import org.springframework.stereotype.Repository;

@Repository
final class GameDaoImpl extends AbstractDao<Game, GameDpo> implements GameDao {

  private static final String COLLECTION_NAME = "game";

  /**
   * Constructor.
   */
  GameDaoImpl() {
    super(COLLECTION_NAME);
  }
}
