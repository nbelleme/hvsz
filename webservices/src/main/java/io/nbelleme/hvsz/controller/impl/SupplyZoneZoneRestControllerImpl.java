package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.controller.api.SupplyZoneRestController;
import io.nbelleme.hvsz.services.api.SupplyZoneService;
import io.nbelleme.hvsz.zone.internal.SupplyZone;
import io.nbelleme.hvsz.mapper.impl.SupplyZoneMapper;
import io.nbelleme.hvsz.zone.transfer.SupplyZoneDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/food-supply")
final class SupplyZoneZoneRestControllerImpl implements SupplyZoneRestController {

  private SupplyZoneService supplyZoneService;

  /**
   * Constructor.
   *
   * @param supplyZoneService the foodSupplyService
   */
  SupplyZoneZoneRestControllerImpl(SupplyZoneService supplyZoneService) {
    this.supplyZoneService = Objects.requireNonNull(supplyZoneService);
  }

  @Override
  @GetMapping("/{zoneId}")
  public SupplyZoneDTO getSupplyZone(@PathVariable("zoneId") Long zoneId) {
    SupplyZone supplyZone = supplyZoneService.get(zoneId);
    return SupplyZoneMapper.toDTO(supplyZone);
  }

  @Override
  @GetMapping("/all")
  public List<SupplyZoneDTO> getAllSupplyZone() {
    List<SupplyZone> supplyZones = supplyZoneService.getAll();
    return SupplyZoneMapper.toDTO(supplyZones);
  }

  @Override
  @GetMapping("/{zoneId}/empty")
  public boolean isEmpty(@PathVariable(value = "zoneId") Long zoneId) {
    return supplyZoneService.get(zoneId).getLevel() <= 0;
  }
}
