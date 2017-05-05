package io.resourcepool.hvsz.controller.web;

import io.resourcepool.hvsz.common.Assert;
import io.resourcepool.hvsz.common.exceptions.NoHumanLeftException;
import io.resourcepool.hvsz.game.Game;
import io.resourcepool.hvsz.game.GameService;
import io.resourcepool.hvsz.humans.HumanService;
import io.resourcepool.hvsz.humans.Life;
import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.humans.SafeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@RequestMapping("/safe-zone")
public class SafeZoneController {

  @Autowired
  private HumanService humanService;

  @Autowired
  private SafeZoneService zoneService;

  @Autowired
  private GameService gameService;


  /**
   * Show the safe-zone selection view.
   *
   * @param model Model
   * @return String (safe-zone vue)
   */
  @GetMapping()
  public String showAvailableZones(Model model) {
    Game game = gameService.get();
    Assert.gameOngoing(game);
    List<SafeZone> zones = zoneService.getSafeZones();
    model.addAttribute("zones", zones);
    return "safe-zone/index";
  }

  /**
   * Show the safe-zone view.
   *
   * @param zoneId zoneId
   * @param model  Model
   * @return String (safe-zone vue)
   */
  @GetMapping("/{zoneId}")
  public String showSafeZoneDashboard(@PathVariable Long zoneId, Model model) {
    Game active = gameService.get();
    Assert.gameOngoing(active);
    SafeZone zone = zoneService.getSafeZone(zoneId);
    model.addAttribute("zone", zone);
    model.addAttribute("remainingTime", active.getStatus().getRemainingTime());
    model.addAttribute("humanTickets", active.getConfig().getHumanTickets());
    model.addAttribute("duration", active.getConfig().getGameDuration());
    model.addAttribute("humansOnField", active.getStatus().getCurrentHumansOnField());
    model.addAttribute("remainingHumanTickets", active.getStatus().getRemainingHumanTickets());
    model.addAttribute("status", active.getStatus().getGameState());
    return "safe-zone/safe-zone";
  }

  /**
   * Offload some food into the safe-zone supplies.
   *
   * @param zoneId    SupplyZone id
   * @param lifeToken lifeToken
   * @param model     Model
   * @return String (human vue)
   */
  @PostMapping("/{zoneId}/offload")
  public String offloadFood(@PathVariable Long zoneId, @RequestParam(value = "lifeToken") int lifeToken, Model model) {
    Game g = gameService.get();
    Assert.gameActive(g);
    Life life = g.getStatus().getLifeByToken(lifeToken);
    if (life == null) {
      model.addAttribute("message", "Token invalide, essayes encore !");
    } else if (!life.isAlive()) {
      model.addAttribute("message", "Tu es mort !");
    } else {
      //SafeZone s = g.getSafeZones().stream().filter(foodSupply -> foodSupply.getId().equals(zoneId)).findFirst().get();
      int amountRefilled = zoneService.refill(zoneId, lifeToken);
      model.addAttribute("amountRefilled", amountRefilled);
    }
    SafeZone safeZone = zoneService.getSafeZone(zoneId);
    model.addAttribute("zone", safeZone);
    return "safe-zone/safe-zone";
  }


  /**
   * Spawn a new human life in safe-zone.
   *
   * @param model  Model
   * @param zoneId id of the safe zone
   * @return String (human)
   */
  @GetMapping("/{zoneId}/spawn")
  public String spawnHuman(@PathVariable Long zoneId, Model model) {
    Game active = gameService.get();
    Assert.gameActive(active);
    if (!humanService.canSpawn()) {
      model.addAttribute("spawnResultMsg", "Toutes les vies sont en cours d'utilisation (les zombies ont un petit appétit) ;-(");
    } else {
      Life life = humanService.spawn();
      int lifeToken = life.getToken();
      model.addAttribute("spawnResultMsg", "Une nouvelle vie pour toi <3  token: " + lifeToken);
    }
    SafeZone safeZone = zoneService.getSafeZone(zoneId);
    model.addAttribute("zone", safeZone);
    model.addAttribute("remainingTime", active.getStatus().getRemainingTime());
    model.addAttribute("humanTickets", active.getConfig().getHumanTickets());
    model.addAttribute("duration", active.getConfig().getGameDuration());
    model.addAttribute("humansOnField", active.getStatus().getCurrentHumansOnField());
    model.addAttribute("remainingHumanTickets", active.getStatus().getRemainingHumanTickets());
    model.addAttribute("status", active.getStatus().getGameState());
    return "safe-zone/safe-zone";
  }

  /**
   * @return the game-over page when no human is left.
   */
  @ExceptionHandler({NoHumanLeftException.class})
  public String humansAreDead() {
    return "redirect:/game/over?error=no-human-left";
  }

}
