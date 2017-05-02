package io.resourcepool.hvsz;

import org.junit.Ignore;
import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.*;
import io.resourcepool.hvsz.service.DashboardService;
import io.resourcepool.hvsz.service.HumanService;
import io.resourcepool.hvsz.service.ZombieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class ServiceTests {
    private static final int NBLIFE = 100;
    private static final int GAME_DURATION = 100;
    private static final int DIFFICULTY = 1;
    private static final int NBHUMAN = 10;
    private static final int NBZONBIE = 3;
    private static final int NBSAFE_ZONE = 3;
    private static final int NBSUPPLY_ZONE = 5;
    private static final int NBSUPPLY_ZONE_RESOURCES = 400;

    @Autowired
    private DaoMapDb dao;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private ZombieService zombieService;

    @Autowired
    private HumanService humanService;

    @Before
    public void init () {
        Game gameTest = initGame();
        dao.set(1L, gameTest);
    }

    @Test
    public void dashboardServiceGetLifeLeft() {
        int result = dashboardService.getLifeLeft();
        assertTrue("Test getLife Left expected : " + NBLIFE + " current : " + result, result == NBLIFE);
    }

    @Test
    public void dashboardServiceGetHumanAlive() {
        int result = dashboardService.getHuman();
        assertTrue("Test getHuman Left expected : " + 0 + " current : " + result, result == 0);
    }

    @Test
    public void dashboardServiceGetZoneResource() {
        ArrayList<ZoneResource> zone = new ArrayList<>(dashboardService.getZoneResource());
        int result = zone.size();
        assertTrue("Test GetZoneResource expected : " + (NBSAFE_ZONE+NBSUPPLY_ZONE) + " current : " + result, result == (NBSAFE_ZONE+NBSUPPLY_ZONE));
        result = getNumberSupply(zone);
        assertTrue("Test GetZoneSupply Left expected : " + NBSUPPLY_ZONE + " current : " + result, result == NBSUPPLY_ZONE);
        result = getNumberSafe(zone);
        assertTrue("Test GetZoneSafe Left expected : " + NBSAFE_ZONE + " current : " + result, result == NBSAFE_ZONE);
    }
    
    @Test
    public void humanServiceNewLife() {
        int init = dashboardService.getHuman();
        humanService.newLife();
        humanService.newLife();
        int result = dashboardService.getHuman();
        assertTrue("Test getHuman Left expected : " + (init + 2) + " current : " + result, result == (init + 2));
    }

    @Test
    public void zombieServiceKill() {
        String lifeID = humanService.newLife().getToken();
        int init = dashboardService.getHuman();
        zombieService.kill(lifeID);
        int result = dashboardService.getHuman();
        assertTrue("Test getHuman and  Left expected : " + (init - 1) + " current : " + result, result == (init - 1));
    }


    private int getNumberSafe(ArrayList<ZoneResource> zones) {
        int cpt = 0;
        for (int i = 0; i < zones.size(); i++) {
            if (zones.get(i).getClass().equals(SafeZone.class)) {
                cpt++;
            }
        }
        return cpt;
    }
    private int getNumberSupply(ArrayList<ZoneResource> zones) {
        int cpt = 0;
        for (int i = 0; i < zones.size(); i++) {
            if (zones.get(i).getClass().equals(SupplyZone.class)) {
                cpt++;
            }
        }
        return cpt;
    }

    private Game initGame () {
        Game gameTest = new Game();
        GameConfig config = GenericBuilder.of(GameConfig::new)
                .with(GameConfig::setGameDuration, GAME_DURATION)
                .with(GameConfig::setDifficulty, DIFFICULTY)
                .with(GameConfig::setNbHuman, NBHUMAN)
                .with(GameConfig::setNbZombie, NBZONBIE)
                .with(GameConfig::setNbSafezone, NBSAFE_ZONE)
                .with(GameConfig::setNbSafezoneLifes, NBLIFE)
                .with(GameConfig::setNbSupplyZone, NBSUPPLY_ZONE)
                .with(GameConfig::setNbSupplyResources, NBSUPPLY_ZONE_RESOURCES)
                .build();
        gameTest.setConfig(config);

        GameStatus status = GenericBuilder.of(GameStatus::new)
                .with(GameStatus::setHumanPlayers, config.getNbHuman())
                .with(GameStatus::setZombiePlayers, config.getNbZombie())
                .with(GameStatus::setNbHumanAlive, config.getNbHuman())
                .with(GameStatus::setTimeLeft, config.getGameDuration())
                .with(GameStatus::setNbLifeLeft, config.getNbSafezoneLifes())
                .with(GameStatus::setStarted, true)
                .build();
        gameTest.setStatus(status);

        return gameTest;
    }
}
