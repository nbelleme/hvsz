package io.resourcepool.hvsz.controller.web;

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
   *
   * @param model Model
   * @return String (configuration view)
   */
  @GetMapping()
  public String showGameSettingsView(Model model) {
    return "game/settings";
  }

  /**
   * Submit the game config page.
   *
   * @param gameDuration      .
   * @param difficulty        .
   * @param humanTickets      .
   * @param nbSafeZones       .
   * @param nbFoodSupplyZones .
   * @param nbFoodSupplies    .
   * @param maxHumansOnField  .
   * @param maximumFoodTransfer *
   * @param model             Model
   * @return String (redirect to dashboard)
   */
  @PostMapping()
  public String postForm(
    @RequestParam(value = "gameDuration") int gameDuration,
    @RequestParam(value = "difficulty") int difficulty,
    @RequestParam(value = "humanTickets") int humanTickets,
    @RequestParam(value = "maxHumansOnField") int maxHumansOnField,
    @RequestParam(value = "nbSafeZones") int nbSafeZones,
    @RequestParam(value = "nbFoodSupplyZones") int nbFoodSupplyZones,
    @RequestParam(value = "maximumFoodTransfer") int maximumFoodTransfer,
    @RequestParam(value = "nbFoodSupplies") int nbFoodSupplies,
    Model model) {

    GameSettings conf = GenericBuilder.of(GameSettings::new)
      .with(GameSettings::setGameDuration, gameDuration)
      .with(GameSettings::setDifficulty, difficulty)
      .with(GameSettings::setHumanTickets, humanTickets)
      .with(GameSettings::setNbSafeZones, nbSafeZones)
      .with(GameSettings::setNbFoodSupplyZones, nbFoodSupplyZones)
      .with(GameSettings::setMaxHumansOnField, maxHumansOnField)
      .with(GameSettings::setNbFoodSupplies, nbFoodSupplies)
      .with(GameSettings::setMaximumFoodTransfer, maximumFoodTransfer)
      .build();
    confService.set(conf);
    return "redirect:/game";
  }
}
