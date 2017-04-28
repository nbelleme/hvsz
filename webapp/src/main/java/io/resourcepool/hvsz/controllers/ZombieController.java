package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.service.ZombieService;
import io.resourcepool.hvsz.service.ZombieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ZombieController {
    @Autowired
    ZombieServiceImpl zombieService;

    /**
     * Get the zombie page.
     * @param lifeId String
     * @param model Model
     * @return String (zombie vue)
     */
    @RequestMapping("/zombie")
    public String kill(@RequestParam(value = "lifeId", required = false) String lifeId, Model model) {
        if (lifeId != null) {
            if (zombieService.kill(lifeId)) {
                model.addAttribute("message", "one human have been killed");
            } else {
                model.addAttribute("message", "one human have been killed, no more respawn for human");
            }
        }
        return "zombie";
    }
}
