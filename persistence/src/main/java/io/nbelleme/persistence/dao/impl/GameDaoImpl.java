package io.nbelleme.persistence.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import io.nbelleme.hvsz.game.internal.Game;
import io.nbelleme.persistence.dao.api.GameDao;
import io.nbelleme.persistence.dpo.impl.GameDpo;
import org.mongojack.JacksonDBCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GameDaoImpl implements GameDao {

  private static final String COLLECTION_NAME = "game";
  private static final String DATABASE_NAME = "hvsz";
  private static final String DATABASE_URL = "127.0.0.1:27017";

  private final JacksonDBCollection<GameDpo, String> collection;
  private static final Logger LOGGER = LoggerFactory.getLogger(GameDao.class);


  /**
   * Constructor.
   */
  @SuppressWarnings("deprecation")
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
  public Game save(Game game) {
    GameDpo gameDpo = GameDpo.build(game);
    return collection.save(gameDpo).getSavedObject().getDto();
  }

  @Override
  public Optional<Game> get() {
    DBObject dbObject = new BasicDBObject("lastUpdate", -1);
    List<GameDpo> list = collection.find().sort(dbObject).toArray();
    GameDpo gameDpo = list.stream().findFirst().orElse(null);
    return gameDpo != null ? Optional.ofNullable(gameDpo.getDto()) : Optional.empty();

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
