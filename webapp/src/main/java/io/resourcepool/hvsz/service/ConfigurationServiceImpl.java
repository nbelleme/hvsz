package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.GameConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private DaoMapDb dao;

    @Override
    public GameConfig add(GameConfig gameConfig, Long gameId) {
        dao.get(gameId).setConfig(gameConfig);
        return dao.get(gameId).getConfig();
    }
}
