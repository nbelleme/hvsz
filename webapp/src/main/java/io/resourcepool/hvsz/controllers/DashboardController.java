package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.service.DashboardService;
import io.resourcepool.hvsz.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    StatusService statusService;

    /**
     * Get the dashboard page.
     * @param model Model
     * @return String (dashboard vue)
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("nbhumain", dashboardService.getHuman());
        model.addAttribute("nbzombie", dashboardService.getZombie());
        model.addAttribute("time", dashboardService.getTime());
        model.addAttribute("nblife", dashboardService.getLifeLeft());
        model.addAttribute("zones", dashboardService.getZoneResource());
        model.addAttribute("status", statusService.get(1L).getGameState());
        return "dashboard";
    }
}
