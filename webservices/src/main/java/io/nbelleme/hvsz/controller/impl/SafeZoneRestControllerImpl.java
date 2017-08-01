package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.controller.api.SafeZoneRestController;
import io.nbelleme.hvsz.services.api.SafeZoneService;
import io.nbelleme.hvsz.zone.internal.SafeZone;
import io.nbelleme.hvsz.zone.mapper.SafeZoneMapper;
import io.nbelleme.hvsz.zone.transfer.SafeZoneDTO;
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
  public SafeZoneDTO getSafeZone(@PathVariable("zoneId") Long zoneId) {
    SafeZone safeZone = safeZoneService.getSafeZone(zoneId);
    return SafeZoneMapper.toDTO(safeZone);
  }

  @Override
  @GetMapping("/all")
  public List<SafeZoneDTO> getAllSafeZone() {
    List<SafeZone> safeZones = safeZoneService.getSafeZones();
    return SafeZoneMapper.toDTO(safeZones);

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
