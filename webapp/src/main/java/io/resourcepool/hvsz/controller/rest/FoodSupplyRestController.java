package io.resourcepool.hvsz.controller.rest;

import io.resourcepool.hvsz.supply.FoodSupply;
import io.resourcepool.hvsz.supply.FoodSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gdanguy on 05/05/17.
 */
@RestController
@RequestMapping("/api/food-supply")
public class FoodSupplyRestController {
    @Autowired
    private FoodSupplyService foodSupplyService;

    /**
     * Get a foodZone.
     * @param zoneId the id of the foodSupply
     * @return the FoodSupply
     */
    @GetMapping("/{zoneId}")
    public FoodSupply getSupplyZone(@PathVariable("zoneId") Long zoneId) {
        return foodSupplyService.get(zoneId);
    }

    /**
     * Get all foodZone.
     * @return List<FoodSupply>
     */
    @GetMapping("/all")
    public List<FoodSupply> getAllSupplyZone() {
        return foodSupplyService.getAll();
    }

    /**
     * Get if the safe-zone is empty or not.
     * @param zoneId the id of the safe
     * @return true if empty, false else
     */
    @GetMapping("/{zoneId}/empty")
    public boolean isEmpty(@PathVariable(value = "zoneId") Long zoneId) {
        return foodSupplyService.get(zoneId).getLevel() <= 0;
    }
}
