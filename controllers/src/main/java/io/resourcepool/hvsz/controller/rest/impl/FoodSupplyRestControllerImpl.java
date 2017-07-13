package io.resourcepool.hvsz.controller.rest.impl;

import io.resourcepool.hvsz.controller.rest.api.FoodSupplyRestController;
import io.resourcepool.hvsz.services.api.FoodSupplyService;
import io.resourcepool.hvsz.supply.FoodSupply;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/food-supply")
final class FoodSupplyRestControllerImpl implements FoodSupplyRestController {

  private FoodSupplyService foodSupplyService;

  /**
   * Constructor.
   *
   * @param foodSupplyService the foodSupplyService
   */
  FoodSupplyRestControllerImpl(FoodSupplyService foodSupplyService) {
    this.foodSupplyService = Objects.requireNonNull(foodSupplyService);
  }

  @Override
  @GetMapping("/{zoneId}")
  public FoodSupply getSupplyZone(@PathVariable("zoneId") Long zoneId) {
    return foodSupplyService.get(zoneId);
  }

  @Override
  @GetMapping("/all")
  public List<FoodSupply> getAllSupplyZone() {
    return foodSupplyService.getAll();
  }

  @Override
  @GetMapping("/{zoneId}/empty")
  public boolean isEmpty(@PathVariable(value = "zoneId") Long zoneId) {
    return foodSupplyService.get(zoneId).getLevel() <= 0;
  }
}
