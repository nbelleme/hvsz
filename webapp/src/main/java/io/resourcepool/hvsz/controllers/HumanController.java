package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.Zone;
import io.resourcepool.hvsz.persistance.models.ZoneResource;
import io.resourcepool.hvsz.service.HumanService;
import io.resourcepool.hvsz.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HumanController {

    @Autowired
    HumanService humanService;
    @Autowired
    ResourceService resourceService;

    /**
     * Get the human page.
     *
     * @param newLife String
     * @param model   Model
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

    /**
     * Interface for select a safe zone.
     *
     * @param model Model
     * @return String (safe-zone vue)
     */
    @GetMapping("/human/zone")
    public String selectZone(Model model) {
        List<ZoneResource> zones = resourceService.getAllZoneResource();
        Zone zone = zones.get(0);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("zones : " + zones);
        model.addAttribute("zones", zones);
        return "zone";
    }

    /**
     * Interface for select a safe zone.
     *
     * @param model Model
     * @param zone  the zone we want to access
     * @return String (safe-zone vue)
     */
    @PostMapping("/human/zone")
    public String displayZone(@RequestParam(value = "zone") ZoneResource zone, Model model) {
        model.addAttribute("zone", zone);

        if (zone.getClass().equals(SafeZone.class)) {
            //redirect safe zone
            return "safe-zone";
        }
        // redirect supply zone
        return "supply-zone";
    }
}
