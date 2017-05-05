package io.resourcepool.hvsz.controller.rest;

import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.humans.HumanService;
import io.resourcepool.hvsz.humans.Life;
import io.resourcepool.hvsz.zombies.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gdanguy on 05/05/17.
 */
@RestController
@RequestMapping("/api/human")
public class HumainRestController {
    @Autowired
    private HumanService humanService;

    @Autowired
    private ZombieService zService;

    @Autowired
    private GameService gameService;

    /**
     * Kill life by token.
     * @param token life token.
     * @return bool succes?
     */
    @PostMapping("/kill/{token}")
    @ResponseBody
    public Boolean killHuman(@PathVariable(value = "token") Integer token) {
        return zService.kill(token);
    }

    /**
     *  Get a new life, return token.
     *  @return life token
     */
    @PostMapping("/takeLife")
    public Life takeLife() {
        return humanService.spawn();
    }

    /**
     *  Get resource number by life.
     *  @param lifeToken the token of the human
     *  @return the number of resources taken
     */
    @GetMapping("/{lifeToken}/nbResource")
    public int getNbResourceByLife(@PathVariable("lifeToken") int lifeToken) {
        return humanService.getLifeByToken(lifeToken).getNbResources();
    }

    /**
     * Get remaining lives.
     * @return the number of remaining live
     */
    @GetMapping("/remaining")
    public int getRemainingLives() {
        return gameService.getActive().getStatus().getRemainingHumanTickets();
    }

    /**
     * Get current number of human on field.
     * @return the current number of human on field
     */
    @GetMapping("/active")
    public int getActiveLives() {
        return gameService.getActive().getStatus().getCurrentHumansOnField();
    }

}
