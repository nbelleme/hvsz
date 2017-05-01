package io.resourcepool.hvsz.service.impl;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameStatus;
import io.resourcepool.hvsz.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private DaoMapDb dao;

    @Override
    public GameStatus add(GameStatus gameStatus, Long gameId) {
        Game g = dao.get(gameId);
        g.setStatus(gameStatus);
        dao.set(gameId, g);
        return dao.get(gameId).getStatus();
    }

    @Override
    public GameStatus get(Long gameId) {
        return dao.get(gameId).getStatus();
    }
}
