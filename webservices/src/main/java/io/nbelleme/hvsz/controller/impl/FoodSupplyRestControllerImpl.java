package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.controller.api.FoodSupplyRestController;
import io.nbelleme.hvsz.services.api.SupplyZoneService;
import io.nbelleme.hvsz.zone.SupplyZone;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/food-supply")
final class FoodSupplyRestControllerImpl implements FoodSupplyRestController {

  private SupplyZoneService supplyZoneService;

  /**
   * Constructor.
   *
   * @param supplyZoneService the foodSupplyService
   */
  FoodSupplyRestControllerImpl(SupplyZoneService supplyZoneService) {
    this.supplyZoneService = Objects.requireNonNull(supplyZoneService);
  }

  @Override
  @GetMapping("/{zoneId}")
  public SupplyZone getSupplyZone(@PathVariable("zoneId") Long zoneId) {
    return supplyZoneService.get(zoneId);
  }

  @Override
  @GetMapping("/all")
  public List<SupplyZone> getAllSupplyZone() {
    return supplyZoneService.getAll();
  }

  @Override
  @GetMapping("/{zoneId}/empty")
  public boolean isEmpty(@PathVariable(value = "zoneId") Long zoneId) {
    return supplyZoneService.get(zoneId).getLevel() <= 0;
  }
}
