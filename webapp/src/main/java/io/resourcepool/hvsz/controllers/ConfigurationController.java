package io.resourcepool.hvsz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConfigurationController {

  @GetMapping("/configuration")
  public String showForm(Model model) {
    return "configuration";
  }

  @PostMapping("/configuration")
  public String postForm(Model model) {




    return "configuration";
  }

}
