package io.nbelleme.persistence.dao.impl;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import io.nbelleme.hvsz.game.Game;
import io.nbelleme.persistence.dao.api.GameDao;
import io.nbelleme.persistence.dpo.impl.GameDpo;
import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
final class GameDaoImpl implements GameDao {

  private static final String COLLECTION_NAME = "game";
  private static final String DATABASE_NAME = "hvsz";
  private static final String DATABASE_URL = "127.0.0.1:27017";

  private final JacksonDBCollection<GameDpo, String> collection;
  private static final Logger LOGGER = LoggerFactory.getLogger(GameDao.class);


  /**
   * Constructor.
   */
  GameDaoImpl() {
    LOGGER.info("Start initialize GameDaoImpl");
    MongoClient mongoClient = new MongoClient(DATABASE_URL);
    DBCollection dbCollection = mongoClient
        .getDB(DATABASE_NAME)
        .getCollection(COLLECTION_NAME);


    collection = JacksonDBCollection.wrap(dbCollection, GameDpo.class, String.class);
    LOGGER.info("End initialize GameDaoImpl");
  }

  @Override
  public void save(Game game) {
    GameDpo gameDpo = GameDpo.build(game);
    collection.save(gameDpo);
  }

  @Override
  public Game get() {
    DBCursor<GameDpo> cursor = collection.find();
    List<GameDpo> gameList = cursor.toArray();
    return cursor.toArray().get(0).getDto();
  }

  @Override
  public Game update(Game game) {
    return null;
  }

  @Override
  public Game delete(Game game) {
    return null;
  }
}
