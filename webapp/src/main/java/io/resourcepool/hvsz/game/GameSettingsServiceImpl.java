package io.resourcepool.hvsz.game;

import io.resourcepool.hvsz.persistence.dao.DaoMapDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.resourcepool.hvsz.common.Constants.GAME_ID;

@Service
public class GameSettingsServiceImpl implements GameSettingsService {

    @Autowired
    private DaoMapDb dao;

    @Override
    public void set(GameSettings gameSettings) {
        Game g = dao.get(GAME_ID);
        if (g == null) { // init new game if none found at this ID
            g = new Game(GAME_ID);
            dao.set(GAME_ID, g);
        }
        g.setConfig(gameSettings);
        dao.set(GAME_ID, g);
    }

    @Override
    public GameSettings get() {
        return dao.get(GAME_ID).getConfig();
    }
}
