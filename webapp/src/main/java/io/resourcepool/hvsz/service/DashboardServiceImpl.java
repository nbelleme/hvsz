package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DaoMapDb dao;

    @Override
    public int getLifeLeft() {
        Game game = get();
        Integer result = game.getConfig().getNbSafezoneLifes();
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
        ArrayList<ZoneResource> zoneResources = new ArrayList<ZoneResource>(game.getSafeZones());
        zoneResources.addAll(game.getSupplyZones());
        return zoneResources;
    }

    private Game get() {
        return new Game(dao.get(1L));
    }
}
