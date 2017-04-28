package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.ZoneResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DaoMapDb dao;

    @Override
    public int getLifeLeft() {
        Game game = get();
        Integer result = game.getStatus().getNbLifeLeft();
        if (result == null) {
            return -1;
        }
        return result;
    }

    @Override
    public int getHuman() {
        Game game = get();
        Integer result = game.getStatus().getNbHumanAlive();
        if (result == null) {
            return -1;
        }
        return result;
    }

    @Override
    public int getZombie() {
        Game game = get();
        Integer result = game.getStatus().getZombiePlayers();
        if (result == null) {
            return -1;
        }
        return result;
    }

    @Override
    public int getTime() {
        Game game = get();
        Integer result = game.getStatus().getTimeLeft();
        if (result == null) {
            return -1;
        }
        return result;
    }

    @Override
    public List<ZoneResource> getZoneResource() {
        Game game = get();
        ArrayList<ZoneResource> zoneResources = new ArrayList<>(game.getSafeZones());
        zoneResources.addAll(game.getSupplyZones());
        return zoneResources;
    }

    /**
     * Return the first game.
     * @return Game
     */
    private Game get() {
        return new Game(dao.get(1L));
    }
}
