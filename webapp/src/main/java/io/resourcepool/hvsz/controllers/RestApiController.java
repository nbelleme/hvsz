package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.models.Game;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestApiController {

  @RequestMapping("/game")
  public Game greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Game();
  }
}
