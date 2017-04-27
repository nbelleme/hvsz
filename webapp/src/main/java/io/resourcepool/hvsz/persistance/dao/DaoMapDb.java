package io.resourcepool.hvsz.persistance.dao;

import io.resourcepool.hvsz.persistance.models.Game;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

@Repository
public class DaoMapDb {

    private static final String DB_FILE = "zombie-game.db";
    private static final String DB_NAME = "zombie-game-db";
    private static final String KEY_CONF_PATH = "zombie-game.resources.path";
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoMapDb.class);

    private Map<Long, Game> games = new HashMap<>();

    public DaoMapDb() {
        DB db = DBMaker.fileDB(DB_FILE).closeOnJvmShutdown().make();
        games = (ConcurrentMap) db.hashMap("games").createOrOpen();
    }

    public Game get(Long key) {
        return games.get(key);
    }

    public void set(Long key, Game val) {
        games.put(key, val);
    }

    public List<Game> getAll() {
        return new ArrayList<>(games.values());
    }

}
