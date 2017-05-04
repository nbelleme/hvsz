package io.resourcepool.hvsz.web;

import io.resourcepool.hvsz.common.models.GenericBuilder;
import io.resourcepool.hvsz.game.GameSettings;
import io.resourcepool.hvsz.game.GameSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/game/settings")
@Controller
public class GameSettingsController {

  @Autowired
  private GameSettingsService confService;

  /**
   * Show the game settings view.
   * @param model Model
   * @return String (configuration view)
   */
  @GetMapping("/")
  public String showGameSettingsView(Model model) {
    return "game/settings";
  }

  /**
   * Submit the game config page.
   * @param gameDuration String
   * @param difficulty String
   * @param humanTickets String
   * @param nbSafeZones String
   * @param nbFoodSupplyZones String
   * @param nbSupplyResources String
   * @param model Model
   * @return String (redirect to dashboard)
   */
  @PostMapping("/")
  public String postForm(
          @RequestParam(value = "gameDuration", defaultValue = "30") String gameDuration,
          @RequestParam(value = "difficulty", defaultValue = "0") String difficulty,
          @RequestParam(value = "humanTickets", defaultValue = "30") String humanTickets,
          @RequestParam(value = "nbSafeZones", defaultValue = "2") String nbSafeZones,
          @RequestParam(value = "nbFoodSupplyZones", defaultValue = "2") String nbFoodSupplyZones,
          @RequestParam(value = "nbSupplyResources", defaultValue = "180") String nbSupplyResources,
          Model model) {

    GameSettings conf = GenericBuilder.of(GameSettings::new)
            .with(GameSettings::setGameDuration, Integer.parseInt(gameDuration))
            .with(GameSettings::setDifficulty, Integer.parseInt(difficulty))
            .with(GameSettings::setHumanTickets, Integer.parseInt(humanTickets))
            .with(GameSettings::setNbSafeZones, Integer.parseInt(nbSafeZones))
            .with(GameSettings::setNbFoodSupplyZones, Integer.parseInt(nbFoodSupplyZones))
            .with(GameSettings::setNbFoodSupplies, Integer.parseInt(nbSupplyResources))
            .build();

    confService.set(conf);

    return "redirect:/game";
  }
}
