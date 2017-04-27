package io.resourcepool.hvsz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ZombieController {

    @GetMapping("/zombie")
    public String kill(Model model) {
        return "zombie";
    }
}
