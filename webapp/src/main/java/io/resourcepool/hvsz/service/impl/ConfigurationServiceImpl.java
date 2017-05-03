package io.resourcepool.hvsz.service.impl;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameConfig;
import io.resourcepool.hvsz.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private DaoMapDb dao;

    @Override
    public GameConfig add(GameConfig gameConfig, Long gameId) {
        Game g = dao.get(gameId);
        if (g == null) { // init new game if none found at this ID
            g = new Game(gameId);
            dao.set(gameId, g);
        }
        g.setConfig(gameConfig);
        dao.set(gameId, g);
        return dao.get(gameId).getConfig();
    }

    @Override
    public GameConfig get(Long gameId) {
        return dao.get(gameId).getConfig();
    }
}
