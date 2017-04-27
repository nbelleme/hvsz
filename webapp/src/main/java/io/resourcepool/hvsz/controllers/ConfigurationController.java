package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.models.GameConfig;
import io.resourcepool.hvsz.persistance.models.GenericBuilder;
import io.resourcepool.hvsz.service.ConfigurationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfigurationController {

  @Autowired
  private ConfigurationServiceImpl confService;

  @GetMapping("/configuration")
  public String showForm(Model model) {
    return "configuration";
  }

  @PostMapping("/configuration")
  public String postForm(
          @RequestParam(value = "gameDuration", defaultValue = "30") String gameDuration,
          @RequestParam(value = "difficulty", defaultValue = "0") String difficulty,
          @RequestParam(value = "nbHuman", defaultValue = "30") String nbHuman,
          @RequestParam(value = "nbZombie", defaultValue = "10") String nbZombie,
          @RequestParam(value = "nbSafezone", defaultValue = "2") String nbSafezone,
          @RequestParam(value = "nbSafezoneLifes", defaultValue = "40") String nbSafezoneLifes,
          @RequestParam(value = "nbSupplyZone", defaultValue = "2") String nbSupplyZone,
          @RequestParam(value = "nbSupplyResources", defaultValue = "180") String nbSupplyResources,
          Model model) {

    GameConfig conf = GenericBuilder.of(GameConfig::new)
            .with(GameConfig::setGameDuration, Integer.parseInt(gameDuration))
            .with(GameConfig::setDifficulty, Integer.parseInt(difficulty))
            .with(GameConfig::setNbHuman, Integer.parseInt(nbHuman))
            .with(GameConfig::setNbZombie, Integer.parseInt(nbZombie))
            .with(GameConfig::setNbSafezone, Integer.parseInt(nbSafezone))
            .with(GameConfig::setNbSafezoneLifes, Integer.parseInt(nbSafezoneLifes))
            .with(GameConfig::setNbSupplyZone, Integer.parseInt(nbSupplyZone))
            .with(GameConfig::setNbSupplyResources, Integer.parseInt(nbSupplyResources))
            .build();

    confService.add(conf, 1L);

    return "redirect:dashboard";
  }
}
