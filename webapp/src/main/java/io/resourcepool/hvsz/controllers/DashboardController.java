package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.service.DashboardService;
import io.resourcepool.hvsz.service.DashboardServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        DashboardService dashboardService = new DashboardServiceImpl();
        model.addAttribute("nbhumain", dashboardService.getHuman());
        model.addAttribute("nbzombie", dashboardService.getZombie());
        model.addAttribute("time",  dashboardService.getTime());
        model.addAttribute("nblife", dashboardService.getLifeLeft());
        model.addAttribute("zones",dashboardService.getZoneResource());

        return "dashboard";
    }

}