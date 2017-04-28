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
     * @param model Model
     * @return String (human vue)
     */
    @GetMapping("/resource/drop")
    public String dropResource(@RequestParam(value = "selectZone", required = false) String safeZone, Model model) {
        if (safeZone != null) {
            Game g = dao.get(1L);
            SafeZone s = g.getSafeZones().get(Integer.parseInt(safeZone));
            model.addAttribute("nbResources", s.drop(1) + " resource has been dropped : supply zone n°" + ID_SUPPLY_ZONE + " contains :" + s.getResource() + "resources");
            dao.set(1L, g);
        }
        return "human";
    }

    /**
     * Take a resource.
     * @param supplyZone SupplyZone id
     * @param model Model
     * @return String (supply-zone vue)
     */
    @GetMapping("/resource/get")
    public String resourceGet(@RequestParam(value = "supplyZone", required = false) String supplyZone, Model model) {

        if (supplyZone != null) {
            Game g = dao.get(1L);
            SupplyZone s = g.getSupplyZones().get(Integer.parseInt(supplyZone));
            model.addAttribute("nbSupplyResources", s.getResource(1) + " resource has been taken : remaining resources :" + s.getResource());
            dao.set(1L, g);
        }
        return "supply-zone";
    }
}