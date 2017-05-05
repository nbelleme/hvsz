package io.resourcepool.hvsz.controller.web;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.zombies.ZombieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 03/05/2017
 */
@Controller
@RequestMapping("/zombie-hq")
public class ZombieHQController {

  @Autowired
  ZombieService zombieService;


  @Autowired
  GameService gameService;

  /**
   * Show the zombie HQ view.
   *
   * @param model the model
   * @return String (zombie HQ view)
   */
  @GetMapping()
  public String get(Model model) {
    Game active = gameService.getActive();
    Assert.gameOngoing(active);
    model.addAttribute("remainingTime", active.getStatus().getRemainingTime());
    model.addAttribute("humanTickets", active.getConfig().getHumanTickets());
    model.addAttribute("duration", active.getConfig().getGameDuration());
    model.addAttribute("humansOnField", active.getStatus().getCurrentHumansOnField());
    model.addAttribute("remainingHumanTickets", active.getStatus().getRemainingHumanTickets());
    model.addAttribute("status", active.getStatus().getGameState());
    return "zombie-hq/zombie-hq";
  }

  /**
   * Kill a human thanks to its life id.
   * This ensures the loss of all the food it was carrying.
   *
   * @param lifeToken .
   * @param model     .
   * @return .
   */
  @PostMapping("/kill")
  public String kill(@RequestParam(value = "lifeToken") int lifeToken, Model model) {
    Game active = gameService.getActive();
    Assert.gameOngoing(active);
    if (zombieService.kill(lifeToken)) {
      model.addAttribute("message", "one human has been killed. There are " + active.getStatus().getRemainingHumanTickets() + " humans left alive.");
    } else {
      model.addAttribute("message", "No human alive with this token!");
    }
    return "zombie-hq/zombie-hq";
  }
}
