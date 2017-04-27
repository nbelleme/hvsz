package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanServiceImpl implements HumanService {

    @Autowired
    private DaoMapDb dao;

    @Override
    public boolean newLife() {
        Game g = dao.get(1L);

        GameStatus status = g.getStatus();

        if(status.getNbLifeLeft() <= 0) {
            return false;
        } else {
            status.setNbLifeLeft(status.getNbLifeLeft() - 1);
            g.setStatus(status);
            dao.set(1L, g);
            return true;
        }
    }
}
