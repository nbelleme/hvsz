package io.resourcepool.hvsz.service;

import io.resourcepool.hvsz.persistance.models.*;
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
        final String uri = "http://localhost:8080/api/game/1";
        RestTemplate restTemplate = new RestTemplate();
        return new Game(restTemplate.getForObject(uri, Game.class));
    }
}
