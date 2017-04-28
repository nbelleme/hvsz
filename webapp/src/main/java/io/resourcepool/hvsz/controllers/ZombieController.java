package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.service.ZombieService;
import io.resourcepool.hvsz.service.ZombieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ZombieController {

    /**
     * Get the zombie page.
     * @param kill String
     * @param model Model
     * @return String (zombie vue)
     */
    @RequestMapping("/zombie")
    public String kill(@RequestParam(value = "kill", required = false) String kill, Model model) {
        if (kill != null) {
            ZombieService zombieService = new ZombieServiceImpl();
            if (zombieService.kill()) {
                model.addAttribute("message", "one human have been killed");
            } else {
                model.addAttribute("message", "one human have been killed, no more respawn for human");
            }
        }
        return "zombie";
    }
}
