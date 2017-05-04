package io.resourcepool.hvsz;

import io.resourcepool.hvsz.game.GameSettingsService;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameSettings;
import io.resourcepool.hvsz.game.Status;
import io.resourcepool.hvsz.supply.ZoneService;
import io.resourcepool.hvsz.humans.HumanService;
import io.resourcepool.hvsz.humans.Life;
import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.service.*;
import io.resourcepool.hvsz.supply.FoodSupply;
import io.resourcepool.hvsz.zombies.ZombieService;
import org.junit.Ignore;
import io.resourcepool.hvsz.common.models.*;
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
    private ZoneService zoneService;

    @Autowired
    private StatusService statusService;

    @Autowired
    private GameSettingsService gameSettingsService;

    @Before
    public void init () {
        initGame();
    }

    @Test
    public void dashboardServiceGetLifeLeft() {
        int result = dashboardService.getRemainingHumanTickets();
        assertTrue("Test getLife Left expected : " + NBLIFE + " current : " + result, result == NBLIFE);
    }

    @Test
    public void dashboardServiceGetHumanAlive() {
        int result = dashboardService.getHumanTickets();
        assertTrue("Test getHumanTickets Left expected : " + 0 + " current : " + result, result == 0);
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
        int init = dashboardService.getHumanTickets();
        humanService.spawn();
        humanService.spawn();
        int result = dashboardService.getHumanTickets();
        assertTrue("Test getHumanTickets expected : " + (init + 2) + " current : " + result, result == (init + 2));
    }

    @Test
    public void humanServiceGetResourcesBySupplyZoneID() {
        Life human = humanService.spawn();
        FoodSupply foodSupply = zoneService.getSupplyZone(0);
        int init = foodSupply.getLevel();
        int result = humanService.getResources(foodSupply.getId(),5,human.getId());
        assertTrue("Test GetResourcesBySupplyZone expected : " + (init-5) + " current : " + (init-result), (init-5) == (init-result));
    }

    @Test
    public void resourceServiceGetSupplyZoneInit() {
        FoodSupply foodSupply = zoneService.getSupplyZone(0);
        int init = foodSupply.getLevel();
        assertTrue("Test supplyzone.getLevel() expected : " + (NBSUPPLY_ZONE_RESOURCES/NBSUPPLY_ZONE) + " current : " + init, init == (NBSUPPLY_ZONE_RESOURCES/NBSUPPLY_ZONE));
    }

    @Test
    public void resourceServiceGetSafeZoneInit() {
        SafeZone safeZone = zoneService.getSafeZone(0);
        int init = safeZone.getLevel();
        assertTrue("Test safezone.getLevel() expected : " + NBSAFE_ZONE_RESOURCES + " current : " + init, init == NBSAFE_ZONE_RESOURCES);
    }

    @Test
    public void resourceServiceGetAllSupplyZone() {
        ArrayList<ZoneResource> zone = new ArrayList<>(zoneService.getAllSupplyZone());
        int result = zone.size();
        assertTrue("Test getAllSupplyZone expected : " + NBSUPPLY_ZONE + " current : " + result, result == NBSUPPLY_ZONE);
    }

    @Test
    public void resourceServiceGetAllSafeZone() {
        ArrayList<ZoneResource> zone = new ArrayList<>(zoneService.getSafeZones());
        int result = zone.size();
        assertTrue("Test getSafeZones expected : " + NBSAFE_ZONE + " current : " + result, result == NBSAFE_ZONE);
    }

    @Test
    public void resourceServiceGetBySupplyZone() {
        FoodSupply foodSupply = zoneService.getSupplyZone(0);
        int init = foodSupply.getLevel();
        int result = zoneService.get(foodSupply,5);
        assertTrue("Test resourceServiceGetBySupplyZone expected : " + (init-5) + " current : " + (init-result), (init-5) == (init-result));
    }

    @Test
    public void resourceServiceGetByID() {
        FoodSupply foodSupply = zoneService.getSupplyZone(0);
        int init = foodSupply.getLevel();
        int result = zoneService.get(0,5);
        assertTrue("Test resourceServiceGetByID expected : " + (init-5) + " current : " + (init-result), (init-5) == (init-result));
    }

    @Test
    public void resourceServiceHumanDropByID() {
        Life life = humanService.spawn();
        humanService.getResources(0,5,life.getId());
        SafeZone safeZone = zoneService.getSafeZone(0);
        int init = safeZone.getLevel();
        int result = zoneService.dropById(0, 5, life.getId());
        assertTrue("Test resourceServiceHumanDropByID expected : " + (init+5) + " current : " + (init+result), (init+5) == (init+result));
    }

    @Test
    public void resourceServiceHumanDropByIDLessQuantite() {
        Life life = humanService.spawn();
        humanService.getResources(0,2,life.getId());
        SafeZone safeZone = zoneService.getSafeZone(0);
        int init = safeZone.getLevel();
        int result = zoneService.dropById(0, 5, life.getId());
        assertTrue("Test resourceServiceHumanDropByIDLessQuantite expected : " + (init+2) + " current : " + (init+result), (init+2) == (init+result));
    }

    @Test
    public void resourceServiceHumanDropByIDInvalidQuantite() {
        Life life = humanService.spawn();
        humanService.getResources(0,5,life.getId());
        SafeZone safeZone = zoneService.getSafeZone(0);
        int init = safeZone.getLevel();
        int result = zoneService.dropById(0, -2, life.getId());
        assertTrue("Test resourceServiceHumanDropByIDInvalidQuantite expected : " + init + " current : " + (init-result), init == (init-result));
    }

    @Test
    public void resourceServiceDecreaseSafeZones() {
        List<SafeZone> safeZonesInit = zoneService.getSafeZones();
        zoneService.decreaseSafezones(5);
        List<SafeZone> safeZonesResult = zoneService.getSafeZones();
        for(int i =0; i < safeZonesInit.size(); i++) {
            assertTrue("Test resourceServiceDecreaseSafeZones expected : " + (safeZonesInit.get(i).getLevel() + 5) + " current : " + safeZonesResult.get(i).getLevel(), (safeZonesInit.get(i).getLevel() + 5) == safeZonesResult.get(i).getLevel());
        }
    }

    @Test
    public void resourceServiceDecreaseSafeZonesInvalidValue() {
        ArrayList<SafeZone> safeZonesInit = new ArrayList(zoneService.getSafeZones());
        zoneService.decreaseSafezones(-5);
        ArrayList<SafeZone> safeZonesResult = new ArrayList(zoneService.getSafeZones());
        for(int i =0; i < safeZonesInit.size(); i++) {
            assertTrue("Test resourceServiceDecreaseSafeZones expected : " + safeZonesInit.get(i).getLevel() + " current : " + safeZonesResult.get(i).getLevel(), safeZonesInit.get(i).getLevel() == safeZonesResult.get(i).getLevel());
        }
    }

    @Test
    public void zombieServiceKill() {
        String lifeID = humanService.spawn().getToken();
        int init = dashboardService.getHumanTickets();
        zombieService.kill(lifeID);
        int result = dashboardService.getHumanTickets();
        assertTrue("Test kill expected : " + (init - 1) + " current : " + result, result == (init - 1));
    }

    @Test
    public void zombieServiceKillInvalidID() {
        int init = dashboardService.getHumanTickets();
        zombieService.kill("-1");
        int result = dashboardService.getHumanTickets();
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
            if (zones.get(i).getClass().equals(FoodSupply.class)) {
                cpt++;
            }
        }
        return cpt;
    }

    private void initGame () {
        Game gameTest = new Game();
        GameSettings config = GenericBuilder.of(GameSettings::new)
                .with(GameSettings::setGameDuration, GAME_DURATION)
                .with(GameSettings::setDifficulty, DIFFICULTY)
                .with(GameSettings::setHumanTickets, 0)
                .with(GameSettings::setNbZombie, NBZONBIE)
                .with(GameSettings::setNbSafeZones, NBSAFE_ZONE)
                .with(GameSettings::setNbSafezoneLifes, NBLIFE)
                .with(GameSettings::setNbFoodSupplyZones, NBSUPPLY_ZONE)
                .with(GameSettings::setNbFoodSupplies, NBSUPPLY_ZONE_RESOURCES)
                .build();
        gameTest.setConfig(config);

        gameSettingsService.add(config, 1L);

        Status status = GenericBuilder.of(Status::new)
                .with(Status::setHumanPlayers, config.getHumanTickets())
                .with(Status::setZombiePlayers, config.getNbZombie())
                .with(Status::setCurrentHumansOnField, config.getHumanTickets())
                .with(Status::setRemainingTime, config.getGameDuration())
                .with(Status::setRemainingHumanTickets, config.getNbSafezoneLifes())
                .with(Status::setStarted, true)
                .build();
        gameTest.setStatus(status);

        statusService.add(status, 1L);

        ArrayList<FoodSupply> foodSupplies = new ArrayList<>();
        int nbSupplyZones = config.getNbFoodSupplyZones();
        int nbSupplyResources = config.getNbFoodSupplies();
        for (int i = 0; i < config.getNbFoodSupplyZones(); i++) {
            foodSupplies.add(new FoodSupply(i, nbSupplyResources / nbSupplyZones));
        }
        zoneService.setSupplyZones(foodSupplies);

        ArrayList<SafeZone> safeZones = new ArrayList<>();
        int nbSafeZones = config.getNbSafeZones();
        for (int i = 0; i < nbSafeZones; i++) {
            safeZones.add(new SafeZone(i, 25, 100));
        }
        zoneService.setSafeZones(safeZones);
    }
}
