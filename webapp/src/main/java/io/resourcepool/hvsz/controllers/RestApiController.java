package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameConfig;
import io.resourcepool.hvsz.persistance.models.GenericBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RestApiController {

  @Autowired
  private DaoMapDb dao;


  @RequestMapping(value = "/game", method = RequestMethod.GET)
  public @ResponseBody List<Game> greeting(@RequestParam(value="name", defaultValue="World") String name) {
    List<Game> games = dao.getAll();
    return games;
  }

  @RequestMapping(value = "/game", method = RequestMethod.POST)
  public @ResponseBody Game newGame(@RequestParam(value="key", defaultValue="1") String key) {
    Long newKey = Long.parseLong(key);
    dao.set(newKey, new Game(newKey)); //use index, not id
    Game g = dao.get(newKey);
    return g;
  }

  @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
  public @ResponseBody Game getGame(@PathVariable(value = "id") Long id) {
    return dao.get(id); //use index, not id
  }

  @RequestMapping(value = "/game/{id}/config", method = RequestMethod.POST)
  public @ResponseBody
  GameConfig setGameConfig(@PathVariable(value = "id") Long id,
                           @RequestParam(value="humanLives", defaultValue="20") String humanLives,
                           @RequestParam(value="gameLength", defaultValue="30") String gameLength,
                           @RequestParam(value="resourceDrainRate", defaultValue="15") String resourceDrainRate,
                           @RequestParam(value="nbSafeZones", defaultValue="1") String nbSafeZones,
                           @RequestParam(value="nbSupplyZones", defaultValue="2") String nbSupplyZones,
                           @RequestParam(value="supplyZonesStorage", defaultValue="2,2") String supplyZonesStorage) {
    Game g = dao.get(id);

    ArrayList<Integer> storages = new ArrayList<>(); // supplyZonesStorage;
    String[] storage = supplyZonesStorage.split(",");
    for(String s : storage) {
      storages.add(Integer.parseInt(s));
    }

    GameConfig config = GenericBuilder.of(GameConfig::new)
        .with(GameConfig::setHumansLives, Integer.parseInt(humanLives))
        .with(GameConfig::setGameLength, Integer.parseInt(gameLength))
        .with(GameConfig::setNbSafeZones, Integer.parseInt(nbSafeZones))
        .with(GameConfig::setNbSupplyZones, Integer.parseInt(nbSupplyZones))
        .with(GameConfig::setResourceDrainRate, Integer.parseInt(resourceDrainRate))
        .with(GameConfig::setSupplyZonesStorage, storages)
        .build();
    g.setConfig(config);
    dao.set(g.getId(), g); //use index, not id

    return dao.get(id).getConfig(); //use index, not id
  }

}
