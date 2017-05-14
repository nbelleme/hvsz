package io.resourcepool.hvsz.controller.rest;

import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.humans.Human;
import io.resourcepool.hvsz.humans.HumanService;
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
public class HumanRestController {
    @Autowired
    private HumanService humanService;

    @Autowired
    private ZombieService zService;

    @Autowired
    private GameService gameService;

    /**
     * Kill life by token.
     *
     * @param token life token.
     * @return bool succes?
     */
    @PostMapping("/kill/{token}")
    @ResponseBody
    public Boolean killHuman(@PathVariable(value = "token") Integer token) {
        return zService.kill(token);
    }

    /**
     * Get a new life, return token.
     *
     * @return life token
     */
    @PostMapping("/newLife")
    public Human getNewLife() {
        return humanService.spawn();
    }

    /**
     * Get resource number by life.
     *
     * @param lifeToken the token of the human
     * @return the number of resources taken
     */
    @GetMapping("/{lifeToken}/nbResource")
    public int getNbResourceByLife(@PathVariable("lifeToken") int lifeToken) {
        return humanService.getHuman(lifeToken).getNbResources();
    }

    /**
     * Get remaining lives.
     *
     * @return the number of remaining live
     */
    @GetMapping("/remaining")
    public int getRemainingLives() {
        return gameService.get().getStatus().getRemainingHumanTickets();
    }

    /**
     * Get current number of human on field.
     *
     * @return the current number of human on field
     */
    @GetMapping("/active")
    public int getActiveLives() {
        return gameService.get().getStatus().getCurrentHumansOnField();
    }

    /**
     * Check if human is still alive.
     *
     * @param token Human's life token to check.
     * @return boolean is human alive
     */
    @GetMapping("/isAlive/{token}")
    public boolean isAlive(@PathVariable("token") int token) {
        return humanService.isAlive(token);
    }


    /**
     * Get human.
     * @param id of the human
     * @return .
     */
    @GetMapping("/{id}")
    public Human getHuman(@PathVariable("id") int id) {
        return humanService.getHuman(id);
    }

}
