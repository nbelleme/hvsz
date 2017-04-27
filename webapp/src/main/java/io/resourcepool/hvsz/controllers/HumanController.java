package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import io.resourcepool.hvsz.service.ConfigurationServiceImpl;
import io.resourcepool.hvsz.service.HumanService;
import io.resourcepool.hvsz.service.HumanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HumanController {

    @Autowired
    private DaoMapDb dao;
    @Autowired
    private ConfigurationServiceImpl confService;


    @GetMapping("/human")
    public String human(@RequestParam(value = "newlife", required = false) String newLife, Model model) {
        if (newLife != null) {
            HumanService humanService = new HumanServiceImpl();
            if (humanService.newLife()) {
                model.addAttribute("newlife", "New life for you <3");
            } else {
                model.addAttribute("newlife", "Sorry no more life ;-(");
            }
        }
        return "human";
    }

}
