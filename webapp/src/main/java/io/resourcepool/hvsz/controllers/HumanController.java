package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.models.GameStateEnum;
import io.resourcepool.hvsz.persistance.models.Life;
import io.resourcepool.hvsz.persistance.models.Zone;
import io.resourcepool.hvsz.persistance.models.ZoneResource;
import io.resourcepool.hvsz.service.HumanService;
import io.resourcepool.hvsz.service.ResourceService;
import io.resourcepool.hvsz.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HumanController {

  @Autowired
  HumanService humanService;
  @Autowired
  ResourceService resourceService;
  @Autowired
  StatusService statusService;

  /**
   * Get the human page.
   *
   * @param newLife String
   * @param model   Model
   * @return String (human)
   */
  @GetMapping("/human")
  public String human(@RequestParam(value = "newlife", required = false) String newLife, Model model) {
    if (!statusService.get(1L).getGameState().equals(GameStateEnum.ONGOING.name())) {
      return "redirect:/game/over";
    }

    if (newLife != null) {
      Life life = humanService.newLife();
      String lifeToken = null;
      if (life != null) {
        lifeToken = life.getToken();
      }
      if (lifeToken != null) {
        model.addAttribute("newlife", "Une nouvelle vie pour toi <3  token: " + lifeToken);
      } else {
        if (humanService.countLifeLeft() > 0) {
          model.addAttribute("newlife", "Toutes les vies sont utilisées");
        } else {
          model.addAttribute("newlife", "Toutes les vies sont en cours d'utilisation (les zombies ont un petit appétit) ;-(");
        }
      }
    }
    return "human";
  }

  /**
   * Interface for select a safe zone.
   *
   * @param model Model
   * @return String (safe-zone vue)
   */
  @GetMapping("/human/zone")
  public String selectZone(Model model) {
    if (!statusService.get(1L).getGameState().equals(GameStateEnum.ONGOING.name())) {
      return "redirect:/game/over";
    }

    List<ZoneResource> zones = resourceService.getAllZoneResource();
    model.addAttribute("zones", zones);
    return "zone";
  }

  /**
   * Interface for select a safe zone.
   *
   * @param model Model
   * @param id    the zone we want to access
   * @param type  the type of zone
   * @return String (safe-zone vue)
   */
  @PostMapping("/human/zone")
  public String displayZone(
      @RequestParam(value = "id") String id,
      @RequestParam(value = "type") String type,
      Model model) {
    if (!statusService.get(1L).getGameState().equals(GameStateEnum.ONGOING.name())) {
      return "redirect:/game/over";
    }

    Zone zone;
    if (type.equals("safezone")) {
      zone = resourceService.getSafeZone(Integer.parseInt(id));
      model.addAttribute("zone", zone);
      model.addAttribute("game_status", statusService.get(1L).getGameState());
      return "safe-zone";
    } else {
      zone = resourceService.getSupplyZone(Integer.parseInt(id));
      model.addAttribute("maxResource", Life.getMaxResources());
      model.addAttribute("zone", zone);
      return "supply-zone";
    }
  }
}
