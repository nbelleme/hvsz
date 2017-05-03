package io.resourcepool.hvsz.service.impl;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameStatus;
import io.resourcepool.hvsz.persistance.models.GenericBuilder;
import io.resourcepool.hvsz.persistance.models.Life;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import io.resourcepool.hvsz.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HumanServiceImpl implements HumanService {

    private static int incrementor = 0;

    @Autowired
    private DaoMapDb dao;


    @Override
    public Life newLife() {
        Game g = dao.get(1L);

        GameStatus status = g.getStatus();

        if (incrementor == 0) {
            setLastId(1L); // in case of reload of base, to avoid having incrementor reset
        }

        if (status.getNbLifeLeft() <= 0) {
            return null;
        } else if (status.getNbHumanAlive() + 1 > status.getHumanPlayers()) {
            return null;
        } else {
            //Decrement nbLifeLeft in game status and increment nbHumanAlive
            status.setNbLifeLeft(status.getNbLifeLeft() - 1);
            status.setNbHumanAlive(status.getNbHumanAlive() + 1);
            //Create new Life
            Life life = GenericBuilder.of(Life::new)
                    .with(Life::setId, incrementor++)
                    .with(Life::setAlive, true)
                    .with(Life::setNbResources, 0)
                    .with(Life::setToken, (incrementor - 1) + UUID.randomUUID().toString().substring(0, 5))
                    .build();
            status.getLives().add(life);
            g.setStatus(status);
            dao.set(1L, g);
            return life;
        }
    }

    /**
     * get life by id.
     *
     * @param id .
     * @return life.
     */
    public Life getLife(Integer id) {
        Game g = dao.get(1L);
        int i = 0;
        for (Life l : g.getStatus().getLives()) {
            if (l.getId() == id) {
                return g.getStatus().getLives().get(i);
            }
            i++;
        }
        return null;
    }

    /**
     * get life by id.
     *
     * @param id .
     * @return life.
     */
    public Integer getLastId(Long id) {
        Game g = dao.get(id);
        int maxId = 0;
        if (g.getStatus().getLives() == null) {
            return maxId;
        }
        for (Life l : g.getStatus().getLives()) {
            if (l.getId() > maxId) {
                maxId = l.getId();
            }
        }
        return maxId;
    }

    /**
     * set last life id from db.
     *
     * @param id game id
     */
    public void setLastId(Long id) {
        Integer maxId = getLastId(id);
        if (incrementor <= maxId) {
            incrementor = maxId;
        }
    }

    /**
     * @param zId ZoneResource id
     * @param qt  Integer : resources amount
     * @param id  Integer : life id
     * @return Integer : amount got
     */
    public Integer getResources(Integer zId, Integer qt, Integer id) {
        Game g = dao.get(1L);
        Life l = g.getStatus().getLife(id);
        SupplyZone z = g.getSupplyZoneById(zId);
        Integer gotR = z.getResource(qt);
        l.addResource(gotR);
        dao.set(1L, g);
        return gotR;
    }
}
