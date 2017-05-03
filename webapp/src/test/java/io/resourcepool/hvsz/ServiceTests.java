package io.resourcepool.hvsz;

import io.resourcepool.hvsz.service.*;
import org.junit.Ignore;
import io.resourcepool.hvsz.persistance.models.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class ServiceTests {
    private static final int NBLIFE = 100;
    private static final int GAME_DURATION = 100;
    private static final int DIFFICULTY = 0;
    private static final int NBHUMAN = 10;
    private static final int NBZONBIE = 3;
    private static final int NBSAFE_ZONE = 3;
    private static final int NBSUPPLY_ZONE = 5;
    private static final int NBSUPPLY_ZONE_RESOURCES = 400;

    //TODO get la valeur
    private static final int NBSAFE_ZONE_RESOURCES = 25;

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private ZombieService zombieService;

    @Autowired
    private HumanService humanService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private ConfigurationService configurationService;

    @Before
    public void init () {
        initGame();
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
        assertTrue("Test GetZoneSupply expected : " + NBSUPPLY_ZONE + " current : " + result, result == NBSUPPLY_ZONE);
        result = getNumberSafe(zone);
        assertTrue("Test GetZoneSafe expected : " + NBSAFE_ZONE + " current : " + result, result == NBSAFE_ZONE);
    }

    @Test
    public void humanServiceNewLife() {
        int init = dashboardService.getHuman();
        humanService.newLife();
        humanService.newLife();
        int result = dashboardService.getHuman();
        assertTrue("Test getHuman expected : " + (init + 2) + " current : " + result, result == (init + 2));
    }

    @Test
    public void humanServiceGetResourcesBySupplyZoneID() {
        Life human = humanService.newLife();
        SupplyZone supplyZone = resourceService.getSupplyZone(0);
        int init = supplyZone.getResource();
        int result = humanService.getResources(supplyZone.getId(),5,human.getId());
        assertTrue("Test GetResourcesBySupplyZone expected : " + (init-5) + " current : " + (init-result), (init-5) == (init-result));
    }

    @Test
    public void resourceServiceGetSupplyZoneInit() {
        SupplyZone supplyZone = resourceService.getSupplyZone(0);
        int init = supplyZone.getResource();
        assertTrue("Test supplyzone.getResource() expected : " + (NBSUPPLY_ZONE_RESOURCES/NBSUPPLY_ZONE) + " current : " + init, init == (NBSUPPLY_ZONE_RESOURCES/NBSUPPLY_ZONE));
    }

    @Test
    public void resourceServiceGetSafeZoneInit() {
        SafeZone safeZone = resourceService.getSafeZone(0);
        int init = safeZone.getResource();
        assertTrue("Test safezone.getResource() expected : " + NBSAFE_ZONE_RESOURCES + " current : " + init, init == NBSAFE_ZONE_RESOURCES);
    }

    @Test
    public void resourceServiceGetAllSupplyZone() {
        ArrayList<ZoneResource> zone = new ArrayList<>(resourceService.getAllSupplyZone());
        int result = zone.size();
        assertTrue("Test getAllSupplyZone expected : " + NBSUPPLY_ZONE + " current : " + result, result == NBSUPPLY_ZONE);
    }

    @Test
    public void resourceServiceGetAllSafeZone() {
        ArrayList<ZoneResource> zone = new ArrayList<>(resourceService.getAllSafeZone());
        int result = zone.size();
        assertTrue("Test getAllSafeZone expected : " + NBSAFE_ZONE + " current : " + result, result == NBSAFE_ZONE);
    }

    @Test
    public void resourceServiceGetBySupplyZone() {
        SupplyZone supplyZone = resourceService.getSupplyZone(0);
        int init = supplyZone.getResource();
        int result = resourceService.get(supplyZone,5);
        assertTrue("Test resourceServiceGetBySupplyZone expected : " + (init-5) + " current : " + (init-result), (init-5) == (init-result));
    }

    @Test
    public void resourceServiceGetByID() {
        SupplyZone supplyZone = resourceService.getSupplyZone(0);
        int init = supplyZone.getResource();
        int result = resourceService.get(0,5);
        assertTrue("Test resourceServiceGetByID expected : " + (init-5) + " current : " + (init-result), (init-5) == (init-result));
    }

    @Test
    public void resourceServiceHumanDropByID() {
        Life life = humanService.newLife();
        humanService.getResources(0,5,life.getId());
        SafeZone safeZone = resourceService.getSafeZone(0);
        int init = safeZone.getResource();
        int result = resourceService.dropById(0, 5, life.getId());
        assertTrue("Test resourceServiceHumanDropByID expected : " + (init+5) + " current : " + (init+result), (init+5) == (init+result));
    }

    @Test
    public void resourceServiceHumanDropByIDLessQuantite() {
        Life life = humanService.newLife();
        humanService.getResources(0,2,life.getId());
        SafeZone safeZone = resourceService.getSafeZone(0);
        int init = safeZone.getResource();
        int result = resourceService.dropById(0, 5, life.getId());
        assertTrue("Test resourceServiceHumanDropByIDLessQuantite expected : " + (init+2) + " current : " + (init+result), (init+2) == (init+result));
    }

    @Test
    public void resourceServiceHumanDropByIDInvalidQuantite() {
        Life life = humanService.newLife();
        humanService.getResources(0,5,life.getId());
        SafeZone safeZone = resourceService.getSafeZone(0);
        int init = safeZone.getResource();
        int result = resourceService.dropById(0, -2, life.getId());
        assertTrue("Test resourceServiceHumanDropByIDInvalidQuantite expected : " + init + " current : " + (init-result), init == (init-result));
    }

    @Test
    public void resourceServiceDecreaseSafeZones() {
        List<SafeZone> safeZonesInit = resourceService.getAllSafeZone();
        resourceService.decreaseSafezones(5);
        List<SafeZone> safeZonesResult = resourceService.getAllSafeZone();
        for(int i =0; i < safeZonesInit.size(); i++) {
            assertTrue("Test resourceServiceDecreaseSafeZones expected : " + (safeZonesInit.get(i).getResource() + 5) + " current : " + safeZonesResult.get(i).getResource(), (safeZonesInit.get(i).getResource() + 5) == safeZonesResult.get(i).getResource());
        }
    }

    @Test
    public void resourceServiceDecreaseSafeZonesInvalidValue() {
        ArrayList<SafeZone> safeZonesInit = new ArrayList(resourceService.getAllSafeZone());
        resourceService.decreaseSafezones(-5);
        ArrayList<SafeZone> safeZonesResult = new ArrayList(resourceService.getAllSafeZone());
        for(int i =0; i < safeZonesInit.size(); i++) {
            assertTrue("Test resourceServiceDecreaseSafeZones expected : " + safeZonesInit.get(i).getResource() + " current : " + safeZonesResult.get(i).getResource(), safeZonesInit.get(i).getResource() == safeZonesResult.get(i).getResource());
        }
    }

    @Test
    public void zombieServiceKill() {
        String lifeID = humanService.newLife().getToken();
        int init = dashboardService.getHuman();
        zombieService.kill(lifeID);
        int result = dashboardService.getHuman();
        assertTrue("Test kill expected : " + (init - 1) + " current : " + result, result == (init - 1));
    }

    @Test
    public void zombieServiceKillInvalidID() {
        int init = dashboardService.getHuman();
        zombieService.kill("-1");
        int result = dashboardService.getHuman();
        assertTrue("Test kill with invalid ID expected : " + init + " current : " + result, result == init);
    }


    //Fonctions utilitaires
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

    private void initGame () {
        Game gameTest = new Game();
        GameConfig config = GenericBuilder.of(GameConfig::new)
                .with(GameConfig::setGameDuration, GAME_DURATION)
                .with(GameConfig::setDifficulty, DIFFICULTY)
                .with(GameConfig::setNbHuman, 0)
                .with(GameConfig::setNbZombie, NBZONBIE)
                .with(GameConfig::setNbSafezone, NBSAFE_ZONE)
                .with(GameConfig::setNbSafezoneLifes, NBLIFE)
                .with(GameConfig::setNbSupplyZone, NBSUPPLY_ZONE)
                .with(GameConfig::setNbSupplyResources, NBSUPPLY_ZONE_RESOURCES)
                .build();
        gameTest.setConfig(config);

        configurationService.add(config, 1L);

        GameStatus status = GenericBuilder.of(GameStatus::new)
                .with(GameStatus::setHumanPlayers, config.getNbHuman())
                .with(GameStatus::setZombiePlayers, config.getNbZombie())
                .with(GameStatus::setNbHumanAlive, config.getNbHuman())
                .with(GameStatus::setTimeLeft, config.getGameDuration())
                .with(GameStatus::setNbLifeLeft, config.getNbSafezoneLifes())
                .with(GameStatus::setStarted, true)
                .build();
        gameTest.setStatus(status);

        statusService.add(status, 1L);

        ArrayList<SupplyZone> supplyZones = new ArrayList<>();
        int nbSupplyZones = config.getNbSupplyZone();
        int nbSupplyResources = config.getNbSupplyResources();
        for (int i = 0; i < config.getNbSupplyZone(); i++) {
            supplyZones.add(new SupplyZone(i, nbSupplyResources / nbSupplyZones));
        }
        resourceService.setSupplyZones(supplyZones);

        ArrayList<SafeZone> safeZones = new ArrayList<>();
        int nbSafeZones = config.getNbSafezone();
        for (int i = 0; i < nbSafeZones; i++) {
            safeZones.add(new SafeZone(i, 25, 100));
        }
        resourceService.setSafeZones(safeZones);
    }
}
