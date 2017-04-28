package io.resourcepool.hvsz.controllers;

import io.resourcepool.hvsz.persistance.dao.DaoMapDb;
import io.resourcepool.hvsz.persistance.models.Game;
import io.resourcepool.hvsz.persistance.models.SafeZone;
import io.resourcepool.hvsz.persistance.models.SupplyZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResourceController {

    @Autowired
    private DaoMapDb dao;

    private static final int ID_SUPPLY_ZONE = 1;

    /**
     * Drop a resource.
     * @param safeZone SupplyZone id
     * @param lifeId life id
     * @param model Model
     * @return String (human vue)
     */
    @PostMapping("/resource/drop")
    public String dropResource(
            @RequestParam(value = "safeZone") String safeZone,
            @RequestParam(value = "lifeId") String lifeId,
            Model model) {

        //TODO: Faire une méthode pour dropper des ressources par un utilisateur (lifeId)
        Game g = dao.get(1L);
        SafeZone s = g.getSafeZones().get(Integer.parseInt(safeZone));
        model.addAttribute("nbResources", s.drop(1) + " resource has been dropped : supply zone n°" + ID_SUPPLY_ZONE + " contains :" + s.getResource() + "resources");
        dao.set(1L, g);
        return "human";
    }

    /**
     * Show the supply-zone view
     * @return String (supply-zone vue)
     */
    @GetMapping("/resource/get")
    public String resourceGet() {
        return "supply-zone";
    }

    /**
     * Take a resource.
     * @param supplyZone Supply zone id
     * @param lifeId life id
     * @param model Model
     * @return String (supply-zone)
     */
    @PostMapping("/resource/get")
    public String resourceGet(
            @RequestParam(value = "supplyZone") String supplyZone,
            @RequestParam(value = "lifeId") String lifeId,
            Model model) {

        //TODO: prendre une ressource en tant qu'utilisateur (lifeId) dans un supplyZone
        Game g = dao.get(1L);
        SupplyZone s = g.getSupplyZones().get(Integer.parseInt(supplyZone));
        model.addAttribute("nbSupplyResources", s.getResource(1) + " resource has been taken : remaining resources :" + s.getResource());
        dao.set(1L, g);
        return "supply-zone";
    }
}