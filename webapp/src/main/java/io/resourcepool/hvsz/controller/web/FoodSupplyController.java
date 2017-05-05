package io.resourcepool.hvsz.controller.web;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.game.GameSettings;
import io.resourcepool.hvsz.game.GameSettingsService;
import io.resourcepool.hvsz.supply.FoodSupply;
import io.resourcepool.hvsz.supply.FoodSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 03/05/2017
 */
@Controller
@RequestMapping("/food-supply")
public class FoodSupplyController {

  @Autowired
  private FoodSupplyService foodSupplyService;

  @Autowired
  private GameSettingsService gameSettingsService;

  @Autowired
  private GameService gameService;

  /**
   * Show the food-supply-zone selection view.
   *
   * @param model Model
   * @return String (food-supply-zone vue)
   */
  @GetMapping()
  public String showAvailableZones(Model model) {
    Game game = gameService.getActive();
    Assert.gameOngoing(game);
    List<FoodSupply> zones = foodSupplyService.getAll();
    model.addAttribute("zones", zones);
    return "food-supply/index";
  }

  /**
   * Show the food supply view.
   *
   * @param zoneId zoneId
   * @param model  Model
   * @return String (supply-zone vue)
   */
  @GetMapping("/{zoneId}")
  public String showFoodSupplyDashboard(@PathVariable Long zoneId, Model model) {
    GameSettings settings = gameSettingsService.get();
    FoodSupply foodSupply = foodSupplyService.get(zoneId);
    model.addAttribute("maxFoodTransfer", settings.getMaximumFoodTransfer());
    model.addAttribute("zone", foodSupply);
    return "food-supply/food-supply";
  }

  /**
   * Take a resource.
   *
   * @param zoneId    SupplyZone id
   * @param amount    amount of food desired
   * @param lifeToken lifeToken
   * @param model     Model
   * @return String (supply-zone)
   */
  @PostMapping("/{zoneId}")
  public String takeFood(@PathVariable Long zoneId,
                         @RequestParam Integer amount,
                         @RequestParam int lifeToken,
                         Model model) {
    GameSettings settings = gameSettingsService.get();
    model.addAttribute("maxFoodTransfer", settings.getMaximumFoodTransfer());
    int foodTaken = foodSupplyService.takeFood(zoneId, lifeToken, amount);
    model.addAttribute("foodTaken", foodTaken);
    FoodSupply s = foodSupplyService.get(zoneId);
    model.addAttribute("zone", s);
    return "food-supply/food-supply";
  }

}
