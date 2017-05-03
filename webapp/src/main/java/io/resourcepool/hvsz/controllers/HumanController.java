package io.resourcepool.hvsz.controllers;


import io.resourcepool.hvsz.persistance.models.GameStateEnum;
import io.resourcepool.hvsz.persistance.models.Life;
import io.resourcepool.hvsz.persistance.models.ZoneResource;
import io.resourcepool.hvsz.service.HumanService;
import io.resourcepool.hvsz.service.ResourceService;
import io.resourcepool.hvsz.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
   * Get a the human life.
   * @param model   Model
   * @param id id of the safe zone
   * @return String (human)
   */
  @GetMapping("/human")
  public String human(@RequestParam(value = "id") String id,
                      Model model) {
    if (!statusService.get(1L).getGameState().equals(GameStateEnum.ONGOING.name())) {
      return "redirect:/game/over";
    }
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
    model.addAttribute("zone", resourceService.getSafeZone(Integer.parseInt(id)));
    return "safe-zone";
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
}
