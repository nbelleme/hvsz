package io.resourcepool.hvsz.controller.rest;

import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.persistence.dao.DaoMapDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gdanguy on 05/05/17.
 */
@RestController
@RequestMapping("/api/game")
public class GameRestController {

    @Autowired
    private DaoMapDb dao;

    /**
    * Get the current game.
    * @return Game
    */
    @GetMapping()
    @ResponseBody
    public Game getGame() {
        return dao.get(1L);
    }


}
