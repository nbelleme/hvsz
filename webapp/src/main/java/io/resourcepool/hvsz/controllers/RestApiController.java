package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


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
    Long newKey = Long.getLong(key);
    dao.set(newKey, new Game(newKey)); //use index, not id
    Game g = dao.get(newKey);
    return g;
  }

  @RequestMapping(value = "/game/{id}", method = RequestMethod.GET)
  public @ResponseBody Game getGame(@PathVariable(value = "id") Long id) {
    return dao.get(id); //use index, not id
  }
}
