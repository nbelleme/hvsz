package io.nbelleme.persistence.dao;

import io.nbelleme.hvsz.game.Game;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Repository
public class DaoMapDb {

  private static final String DB_FILE = "zombie-game.db";
  private static final Logger LOGGER = LoggerFactory.getLogger(DaoMapDb.class);
  private ConcurrentMap<Long, Game> games;


  /**
   * Initizalize a map_db db backed on a file at DB_FILE.
   */
  public DaoMapDb() {
    DB db = DBMaker.fileDB(DB_FILE).closeOnJvmShutdown().make();
//    games = (ConcurrentMap) db.hashMap("games").createOrOpen();
  }

  /**
   * return the game by key.
   *
   * @param key to retrive, default game 1
   * @return game obj
   */
  public Game get(long key) {
    return games.get(key);
  }

  /**
   * save a game by key.
   *
   * @param key key to save to
   * @param val game object to save
   */
  public void set(long key, Game val) {
    games.put(key, val);
  }

  /**
   * return all saved games.
   *
   * @return list of games
   */
  public List<Game> getAll() {
    return new ArrayList<>(games.values());
  }

}
