package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ebiz on 27/04/17.
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Override
    public int getLifeLeft() {
        Game game = get();
        return game.getConfig().getHumansLives();
    }

    @Override
    public int getHuman() {
        Game game = get();
        return game.getStatus().getNbHumanAlive();
    }

    @Override
    public int getZombie() {
        Game game = get();
        return game.getStatus().getZombiePlayers();
    }

    @Override
    public int getTime() {
        Game game = get();
        return game.getStatus().getTimeLeft();
    }

    @Override
    public List<ZoneResource> getZoneResource() {
        Game game = get();
        ArrayList<ZoneResource> zoneResources = new ArrayList<ZoneResource>(game.getSafeZones());
        zoneResources.addAll(game.getSupplyZones());
        return zoneResources;
    }

    private Game get() {
        final String uri = "http://localhost:8080/game/1";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Game.class);
    }
}
