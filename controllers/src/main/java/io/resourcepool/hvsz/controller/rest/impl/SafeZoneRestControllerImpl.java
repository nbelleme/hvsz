package io.resourcepool.hvsz.controller.rest.impl;

import io.resourcepool.hvsz.controller.rest.api.SafeZoneRestController;
import io.resourcepool.hvsz.humans.SafeZone;
import io.resourcepool.hvsz.services.api.SafeZoneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/safe-zone")
public class SafeZoneRestControllerImpl implements SafeZoneRestController {

  private SafeZoneService safeZoneService;

  /**
   * Constructor.
   *
   * @param safeZoneService the SafeZoneService
   */
  public SafeZoneRestControllerImpl(SafeZoneService safeZoneService) {
    this.safeZoneService = Objects.requireNonNull(safeZoneService);
  }

  @Override
  @GetMapping("/{zoneId}")
  public SafeZone getSafeZone(@PathVariable("zoneId") Long zoneId) {
    return safeZoneService.getSafeZone(zoneId);
  }

  @Override
  @GetMapping("/all")
  public List<SafeZone> getAllSafeZone() {
    return safeZoneService.getSafeZones();
  }

  @Override
  @PostMapping("/{zoneId}/drop")
  public Integer dropResource(@PathVariable(value = "zoneId") Long zoneId,
                              @RequestParam(value = "lifeId") int lifeId) {
    return safeZoneService.refill(zoneId, lifeId);
  }

  @Override
  @GetMapping("/{zoneId}/active")
  public boolean isActive(@PathVariable(value = "zoneId") Long zoneId) {
    return safeZoneService.getSafeZone(zoneId).getLevel() > 0;
  }
}
