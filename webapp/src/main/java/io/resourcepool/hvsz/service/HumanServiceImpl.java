package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameStatus;
import io.resourcepool.hvsz.persistance.models.GenericBuilder;
import io.resourcepool.hvsz.persistance.models.Life;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanServiceImpl implements HumanService {

    private static final int MAX_ID = 5;

    @Autowired
    private DaoMapDb dao;

    @Override
    public Integer newLife() {
        Game g = dao.get(1L);

        GameStatus status = g.getStatus();

        if (status.getNbLifeLeft() <= 0) {
            return -1;
        } else {
            //Decrement nbLifeLeft in game status and increment nbHumanAlive
            status.setNbLifeLeft(status.getNbLifeLeft() - 1);
            status.setNbHumanAlive(status.getNbHumanAlive() + 1);
            //Create new Life
            Life life = GenericBuilder.of(Life::new)
                    .with(Life::setId, ((int) (Math.random() * MAX_ID)))
                    .with(Life::setAlive, true)
                    .with(Life::setNbResources, 0)
                    .build();
            status.getLives().add(life);
            g.setStatus(status);
            dao.set(1L, g);
            return life.getId();
        }
    }
}
