package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameConfig;
import io.resourcepool.hvsz.persistance.models.GenericBuilder;
import io.resourcepool.hvsz.service.ConfigurationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RestApiController {

  @Autowired
  private DaoMapDb dao;
  @Autowired
  private ConfigurationServiceImpl confService;

  @RequestMapping(value = "/api/game", method = RequestMethod.GET)
  public @ResponseBody List<Game> greeting(@RequestParam(value="name", defaultValue="World") String name) {
    List<Game> games = dao.getAll();
    return games;
  }

  @RequestMapping(value = "/api/game", method = RequestMethod.POST)
  public @ResponseBody Game newGame(@RequestParam(value="key", defaultValue="1") String key) {
    Long newKey = Long.parseLong(key);
    dao.set(newKey, new Game(newKey)); //use index, not id
    Game g = dao.get(newKey);
    return g;
  }

  @RequestMapping(value = "/api/game/{id}", method = RequestMethod.GET)
  public @ResponseBody Game getGame(@PathVariable(value = "id") Long id) {
    return dao.get(id); //use index, not id
  }

  @PostMapping("/api/game/{id}/config")
  public
  @ResponseBody
  GameConfig setGameConfig(@PathVariable(value = "id") Long id,
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

    confService.add(conf,id);

    return conf;
  }

}
