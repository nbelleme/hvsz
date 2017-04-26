package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.models.Game;
import org.springframework.web.bind.annotation.*;


@RestController
public class RestApiController {

  @RequestMapping(value = "/game", method = RequestMethod.GET)
  public @ResponseBody Game greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Game();
  }
}
