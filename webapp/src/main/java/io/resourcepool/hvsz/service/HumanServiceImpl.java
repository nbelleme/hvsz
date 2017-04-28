package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumanServiceImpl implements HumanService {

    private static final int MAX_ID = 5;
    private static int incrementor = 0;


    @Autowired
    private DaoMapDb dao;

    @Autowired
    private ResourceService resourceService;

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
                    .with(Life::setId, ((int) (Math.random() * MAX_ID))) // TODO update and increment to avoid duplicate id
                    .with(Life::setAlive, true)
                    .with(Life::setNbResources, 0)
                    .build();
            status.getLives().add(life);
            g.setStatus(status);
            dao.set(1L, g);
            return life.getId();
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
        for (Life l : g.getStatus().getLives()) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    /**
     *
     * @param z ZoneResource
     * @param qt Integer : resources amount
     * @param id Integer : life id
     * @return Integer : amount got
     */
    public Integer getResources(SupplyZone z, Integer qt, Integer id) {
        Game g = dao.get(1L);
        //Life l = g.getStatus().getLives().get(id); //getLife(id);//TODO get by id, not index, use service
        Life l = getLife(id);
        Integer gotR = resourceService.get(z, qt);
        //Integer gotR = z.getResource(qt);
        l.addResource(gotR);
        dao.set(1L, g);
        return gotR;
    }

    /**
     *
     * @param zId ZoneResource id
     * @param qt Integer : resources amount
     * @param id Integer : life id
     * @return Integer : amount got
     */
    public Integer getResources(Integer zId, Integer qt, Integer id) {
        Game g = dao.get(1L);
        //Life l = g.getStatus().getLives().get(id); //getLife(id);
        Life l = getLife(id);
        SupplyZone z = g.getSupplyZones().get(zId); //TODO get by id, not index, use zone service
        Integer gotR = resourceService.get(z, qt);
        //Integer gotR = z.getResource(qt);
        l.addResource(gotR);
        dao.set(1L, g);
        return gotR;
    }
}
