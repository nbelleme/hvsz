package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameConfig;
import io.resourcepool.hvsz.persistance.models.GenericBuilder;
import io.resourcepool.hvsz.service.ConfigurationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {

    @Autowired
    private DaoMapDb dao;
    @Autowired
    private ConfigurationServiceImpl confService;

    /**
     * Get all games.
     * @return List<Game>
     */
    @RequestMapping(value = "/api/game", method = RequestMethod.GET)
    @ResponseBody
    public List<Game> getAllGames() {
        return dao.getAll();
    }

    /**
     * Create a new game.
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
     * @param id Long
     * @return Game
     */
    @RequestMapping(value = "/api/game/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Game getGame(@PathVariable(value = "id") Long id) {
        return dao.get(id); //use index, not id
    }

    /**
     * Set a game config by game id.
     * @param id Long
     * @param gameDuration String
     * @param difficulty String
     * @param nbHuman String
     * @param nbZombie String
     * @param nbSafezone String
     * @param nbSafezoneLifes String
     * @param nbSupplyZone String
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

}
