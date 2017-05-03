package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.models.GameStateEnum;
import io.resourcepool.hvsz.service.StatusService;
import io.resourcepool.hvsz.service.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ZombieController {
    @Autowired
    ZombieService zombieService;

    @Autowired
    StatusService gameStatus;

    @Autowired
    StatusService statusService;

    /**
     * Get the zombie page.
     *
     * @return String (zombie vue)
     */
    @GetMapping("/zombie")
    public String get() {
        if (!statusService.get(1L).getGameState().equals(GameStateEnum.ONGOING.name())) {
            return "redirect:/game/over";
        }

        return "zombie";
    }

    /**
     * Kill an human by life id.
     * @param lifeToken .
     * @param model .
     * @return .
     */
    @PostMapping("/zombie/kill")
    public String kill(@RequestParam(value = "lifeToken") String lifeToken, Model model) {
        if (!statusService.get(1L).getGameState().equals(GameStateEnum.ONGOING.name())) {
            return "redirect:/game/over";
        }

        if (zombieService.kill(lifeToken)) {
            model.addAttribute("message", "one human have been killed. There are " + gameStatus.get(1L).getNbHumanAlive() + " humans alive.");
        } else {
            model.addAttribute("message", "No human alive with this token!");
        }
        return "zombie";
    }
}
