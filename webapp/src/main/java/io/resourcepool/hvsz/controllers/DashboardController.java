package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import io.resourcepool.hvsz.persistance.models.Zone;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("nbhumain", "20");
        model.addAttribute("nbzombie", "5");
        model.addAttribute("time", "100");
        model.addAttribute("nblife", "42");

        ArrayList<Zone> listeZones = new ArrayList<>();
        listeZones.add(new SafeZone());
        listeZones.add(new SupplyZone());
        listeZones.add(new SafeZone());

        model.addAttribute("zones",listeZones);

        return "dashboard";
    }

}
