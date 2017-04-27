package io.resourcepool.hvsz.controllers;


import io.resourcepool.hvsz.service.HumanService;
import io.resourcepool.hvsz.service.HumanServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HumanController {
  @GetMapping("/human")
  public String human(@RequestParam(value="newlife", required=false) String newLife, Model model) {
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
