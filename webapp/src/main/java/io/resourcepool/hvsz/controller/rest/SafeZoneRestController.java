package io.resourcepool.hvsz.controller.rest;

import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.humans.SafeZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by gdanguy on 05/05/17.
 */
@RestController
@RequestMapping("/api/safe-zone")
public class SafeZoneRestController {

    @Autowired
    private SafeZoneService safeZoneService;

    /**
     * Get a safe-zone.
     * @param zoneId the id of the safe-zone
     * @return the safe-zone
     */
    @GetMapping("/{zoneId}")
    public SafeZone getSafeZone(@PathVariable("zoneId") Long zoneId) {
        return safeZoneService.getSafeZone(zoneId);
    }

    /**
     * Get all safe-zone.
     * @return List<safe-zone>
     */
    @GetMapping("/all")
    public List<SafeZone> getAllSafeZone() {
        return safeZoneService.getSafeZones();
    }

    /**
     *  Drop resource in zone.
     *  @param zoneId id of zone
     *  @param lifeId the lifeId
     *  @return the amount dropped
     */
    @PostMapping("/{zoneId}/drop")
    public Integer dropResource(@PathVariable(value = "zoneId") Long zoneId,
                                @RequestParam(value = "lifeId") int lifeId) {
        return safeZoneService.refill(zoneId, lifeId);
    }
}
