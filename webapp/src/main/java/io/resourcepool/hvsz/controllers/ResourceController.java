package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.GameStateEnum;
import io.resourcepool.hvsz.persistance.models.Life;
import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import io.resourcepool.hvsz.persistance.models.Zone;
import io.resourcepool.hvsz.service.HumanService;
import io.resourcepool.hvsz.service.ResourceService;
import io.resourcepool.hvsz.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResourceController {

    @Autowired
    private DaoMapDb dao;

    @Autowired
    private HumanService humanService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    StatusService statusService;

    private static final int ID_SUPPLY_ZONE = 1;

    /**
     * Show the safe-zone view.
     *
     * @param zoneId zoneId
     * @param model  Model
     * @return String (safe-zone vue)
     */
    @GetMapping("/resource/drop")
    public String resourceDrop(
            @RequestParam(value = "zoneId") String zoneId,
            Model model) {
        if (!statusService.get(1L).getGameState().equals(GameStateEnum.ONGOING.name())) {
            return "redirect:/game/over";
        }
        
        Zone zone = resourceService.getSafeZone(Integer.parseInt(zoneId));
        model.addAttribute("zone", zone);
        return "safe-zone";
    }

    /**
     * Drop a resource.
     *
     * @param zoneId    SupplyZone id
     * @param lifeToken lifeToken
     * @param model     Model
     * @return String (human vue)
     */
    @PostMapping("/resource/drop")
    public String dropResource(
            @RequestParam(value = "zoneId") String zoneId,
            @RequestParam(value = "lifeToken") String lifeToken,
            Model model) {
        if (!statusService.get(1L).getGameState().equals(GameStateEnum.ONGOING.name())) {
            return "redirect:/game/over";
        }

        Game g = dao.get(1L);
        if (g.getStatus().getLifeByToken(lifeToken) == null) {
            model.addAttribute("message", "Token invalide, essaye encore !");
        } else if (!g.getStatus().getLifeByToken(lifeToken).isAlive()) {
            model.addAttribute("message", "Tu es mort !");
        } else {
            int lifeId = g.getStatus().getLifeByToken(lifeToken).getId();
            int nbRes = humanService.getLife(lifeId).getNbResources();
            int dropRes = resourceService.dropById(Integer.parseInt(zoneId), nbRes, lifeId);
            model.addAttribute("nbDropped", dropRes);
        }
        SafeZone s = g.getSafeZoneById(Integer.parseInt(zoneId));
        model.addAttribute("zone", s);
        return "safe-zone";
    }

    /**
     * Show the supply-zone view.
     *
     * @param zoneId zoneId
     * @param model  Model
     * @return String (supply-zone vue)
     */
    @GetMapping("/resource/get")
    public String resourceGet(
            @RequestParam(value = "zoneId") String zoneId,
            Model model) {
        if (!statusService.get(1L).getGameState().equals(GameStateEnum.ONGOING.name())) {
            return "redirect:/game/over";
        }

        Zone zone = resourceService.getSupplyZone(Integer.parseInt(zoneId));
        model.addAttribute("maxResource", Life.getMaxResources());
        model.addAttribute("zone", zone);
        return "supply-zone";
    }

    /**
     * Take a resource.
     *
     * @param zoneId      SupplyZone id
     * @param nbResWanted number of resource wanted
     * @param lifeToken   lifeToken
     * @param model       Model
     * @return String (supply-zone)
     */
    @PostMapping("/resource/get")
    public String resourceGet(
            @RequestParam(value = "zoneId") String zoneId,
            @RequestParam(value = "nbResWanted") String nbResWanted,
            @RequestParam(value = "lifeToken") String lifeToken,
            Model model) {
        if (!statusService.get(1L).getGameState().equals(GameStateEnum.ONGOING.name())) {
            return "redirect:/game/over";
        }

        Game g = dao.get(1L);
        Life lifeId = g.getStatus().getLifeByToken(lifeToken);
        if (g.getStatus().getLifeByToken(lifeToken) == null) {
            model.addAttribute("message", "Token invalide, essaye encore !");
        } else if (!g.getStatus().getLifeByToken(lifeToken).isAlive()) {
            model.addAttribute("message", "Tu es mort !");
        } else {
            int id = lifeId.getId();
            int gotRes = humanService.getResources(Integer.parseInt(zoneId), Integer.parseInt(nbResWanted), id);
            model.addAttribute("nbTaken", gotRes);
        }
        g = dao.get(1L);
        SupplyZone s = g.getSupplyZoneById(Integer.parseInt(zoneId));
        model.addAttribute("maxResource", Life.getMaxResources());
        model.addAttribute("zone", s);
        return "supply-zone";
    }
}