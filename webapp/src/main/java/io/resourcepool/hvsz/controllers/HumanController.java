package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.service.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HumanController {

    @Autowired
    HumanService humanService;

    /**
     * Get the human page.
     * @param newLife String
     * @param model Model
     * @return String (human)
     */
    @GetMapping("/human")
    public String human(@RequestParam(value = "newlife", required = false) String newLife, Model model) {
        if (newLife != null) {
            Integer lifeToken = humanService.newLife();
            if (lifeToken != -1) {
                model.addAttribute("newlife", "New life for you <3  token: " + lifeToken);
            } else {
                model.addAttribute("newlife", "Sorry no more life ;-(");
            }
        }
        return "human";
    }

}
