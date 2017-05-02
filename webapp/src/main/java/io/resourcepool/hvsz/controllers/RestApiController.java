package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameConfig;
import io.resourcepool.hvsz.persistance.models.GameStatus;
import io.resourcepool.hvsz.persistance.models.GenericBuilder;
import io.resourcepool.hvsz.persistance.models.Life;
import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import io.resourcepool.hvsz.persistance.models.Zone;
import io.resourcepool.hvsz.service.ConfigurationService;
import io.resourcepool.hvsz.service.HumanService;
import io.resourcepool.hvsz.service.ResourceService;
import io.resourcepool.hvsz.service.StatusService;
import io.resourcepool.hvsz.service.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    private DaoMapDb dao;

    @Autowired
    private ConfigurationService confService;

    @Autowired
    private ZombieService zService;

    @Autowired
    private HumanService humanService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private StatusService statusService;

    /**
     * Get all games.
     *
     * @return List all games
     */
    @RequestMapping(value = "/api/game", method = RequestMethod.GET)
    @ResponseBody
    public List<Game> getAllGames() {
        return dao.getAll();
    }

    /**
     * Init a new game with defautl config and start it.
     *
     * @return List all games
     */
    @RequestMapping(value = "/api/init", method = RequestMethod.GET)
    @ResponseBody
    public List<Game> init() {
        newGame("1");
        setGameConfig(1L, "30", "0", "30", "10", "2", "40", "2", "180");
        GameConfig conf = confService.get(1L);

        GameStatus status = GenericBuilder.of(GameStatus::new)
            .with(GameStatus::setHumanPlayers, conf.getNbHuman())
            .with(GameStatus::setZombiePlayers, conf.getNbZombie())
            .with(GameStatus::setNbHumanAlive, 0)
            .with(GameStatus::setTimeLeft, conf.getGameDuration())
            .with(GameStatus::setNbLifeLeft, conf.getNbSafezoneLifes())
            .with(GameStatus::setStarted, true)
            .build();

        statusService.add(status, 1L);

        ArrayList<SupplyZone> supplyZones = new ArrayList<>();
        int nbSupplyZones = conf.getNbSupplyZone();
        int nbSupplyResources = conf.getNbSupplyResources();
        for (int i = 0; i < conf.getNbSupplyZone(); i++) {
            supplyZones.add(new SupplyZone(i, nbSupplyResources / nbSupplyZones));
        }
        resourceService.setSupplyZones(supplyZones);

        ArrayList<SafeZone> safeZones = new ArrayList<>();
        int nbSafeZones = conf.getNbSafezone();
        for (int i = 0; i < conf.getNbSafezone(); i++) {
            safeZones.add(new SafeZone(i, 25, 100));
        }
        resourceService.setSafeZones(safeZones);

        return dao.getAll();
    }

    /**
     * Create a new game.
     *
     * @param key String
     * @return Game
     */
    @RequestMapping(value = "/api/game", method = RequestMethod.POST)
    @ResponseBody
    public Game newGame(@RequestParam(value = "key", defaultValue = "1") String key) {
        Long newKey = Long.parseLong(key);
        dao.set(newKey, new Game(newKey)); //use index, not id
        return dao.get(newKey);
    }

    /**
     * Get a game by id.
     *
     * @param id Long
     * @return Game
     */
    @RequestMapping(value = "/api/game/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Game getGame(@PathVariable(value = "id") Long id) {
        return dao.get(id); //use index, not id
    }

    /**
     * Kill life by token.
     *
     * @param id    game id.
     * @param token life token.
     * @return bool succes?
     */
    @RequestMapping(value = "/api/game/{id}/kill/{token}", method = RequestMethod.POST)
    @ResponseBody
    public Boolean killHuman(@PathVariable("id") Long id, @PathVariable(value = "token") String token) {
        return zService.kill(token); //use index, not id
    }

    /**
     * Get a new life, return token.
     *
     * @param id game id.
     * @return life token
     */
    @RequestMapping(value = "/api/game/{id}/newLife", method = RequestMethod.GET)
    @ResponseBody
    public Life newLife(@PathVariable("id") Long id) {
        return humanService.newLife(); //use index, not id
    }

    /**
     * Set a game config by game id.
     *
     * @param id                Long
     * @param gameDuration      String
     * @param difficulty        String
     * @param nbHuman           String
     * @param nbZombie          String
     * @param nbSafezone        String
     * @param nbSafezoneLifes   String
     * @param nbSupplyZone      String
     * @param nbSupplyResources String
     * @return GameConfig
     */
    @PostMapping("/api/game/{id}/config")
    @ResponseBody
    public GameConfig setGameConfig(@PathVariable(value = "id") Long id,
                                    @RequestParam(value = "gameDuration", defaultValue = "30") String gameDuration,
                                    @RequestParam(value = "difficulty", defaultValue = "0") String difficulty,
                                    @RequestParam(value = "nbHuman", defaultValue = "30") String nbHuman,
                                    @RequestParam(value = "nbZombie", defaultValue = "10") String nbZombie,
                                    @RequestParam(value = "nbSafezone", defaultValue = "2") String nbSafezone,
                                    @RequestParam(value = "nbSafezoneLifes", defaultValue = "40") String nbSafezoneLifes,
                                    @RequestParam(value = "nbSupplyZone", defaultValue = "2") String nbSupplyZone,
                                    @RequestParam(value = "nbSupplyResources", defaultValue = "180") String nbSupplyResources) {

        GameConfig conf = GenericBuilder.of(GameConfig::new)
                .with(GameConfig::setGameDuration, Integer.parseInt(gameDuration))
                .with(GameConfig::setDifficulty, Integer.parseInt(difficulty))
                .with(GameConfig::setNbHuman, Integer.parseInt(nbHuman))
                .with(GameConfig::setNbZombie, Integer.parseInt(nbZombie))
                .with(GameConfig::setNbSafezone, Integer.parseInt(nbSafezone))
                .with(GameConfig::setNbSafezoneLifes, Integer.parseInt(nbSafezoneLifes))
                .with(GameConfig::setNbSupplyZone, Integer.parseInt(nbSupplyZone))
                .with(GameConfig::setNbSupplyResources, Integer.parseInt(nbSupplyResources))
                .build();

        confService.add(conf, id);

        return conf;
    }


    /**
     * Get resource from zone.
     */
    @RequestMapping(value = "/api/game/{id}/zone/{zoneId}/get", method = RequestMethod.POST)
    @ResponseBody
    public Integer getResource(@PathVariable(value = "id") Long id,
                               @PathVariable(value = "zoneId") Integer zoneId,
                               @RequestParam(value = "lifeId") Integer lifeId,
                               @RequestParam(value = "qte") Integer qte) {

        Integer gotRes = humanService.getResources(zoneId, qte, lifeId);

        return gotRes;
    }

    /**
     * Drop resource in zone.
     */
    @RequestMapping(value = "/api/game/{id}/zone/{zoneId}/drop", method = RequestMethod.POST)
    @ResponseBody
    public Integer dropResource(@PathVariable(value = "id") Long id,
                                @PathVariable(value = "zoneId") Integer zoneId,
                                @RequestParam(value = "lifeId") Integer lifeId,
                                @RequestParam(value = "qte") Integer qte) {

        Integer droppedRes = resourceService.dropById(zoneId, qte, lifeId);
        return droppedRes;
    }

    /**
     * Get zone from id.
     *
     * @return
     */
    @RequestMapping(value = "/api/game/{id}/zone/{zoneId}", method = RequestMethod.GET)
    @ResponseBody
    public Zone getZone(@PathVariable(value = "id") Long id,
                        @PathVariable(value = "zoneId") Long zoneId) {
        //Game g = dao.get(id);
        //TODO Use zoneservice
        throw new NotImplementedException();
        //return g.getSafeZones().get(1);
    }

}
