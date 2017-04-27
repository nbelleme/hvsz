package io.resourcepool.hvsz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KillHumanController {

    @RequestMapping("/kill")
    public String kill(Model model) {
        return "kill";
    }
}
