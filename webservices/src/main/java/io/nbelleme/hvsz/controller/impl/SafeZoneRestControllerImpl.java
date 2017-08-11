package io.nbelleme.hvsz.controller.impl;

import io.nbelleme.hvsz.controller.api.SafeZoneRestController;
import io.nbelleme.hvsz.mapper.api.GenericMapper;
import io.nbelleme.hvsz.services.api.SafeZoneService;
import io.nbelleme.hvsz.zone.internal.SafeZone;
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
  private GenericMapper<SafeZone, SafeZoneDTO> safeZoneMapper;

  /**
   * Constructor.
   *
   * @param safeZoneService the SafeZoneService
   * @param safeZoneMapper  safeZoneMapperg
   */
  public SafeZoneRestControllerImpl(SafeZoneService safeZoneService, GenericMapper<SafeZone, SafeZoneDTO> safeZoneMapper) {
    this.safeZoneService = Objects.requireNonNull(safeZoneService);
    this.safeZoneMapper = Objects.requireNonNull(safeZoneMapper);
  }

  @Override
  @GetMapping("/{zoneId}")
  public SafeZoneDTO getSafeZone(@PathVariable("zoneId") Long zoneId) {
    SafeZone safeZone = safeZoneService.getSafeZone(zoneId);
    return safeZoneMapper.toDTO(safeZone);
  }

  @Override
  @GetMapping("/all")
  public List<SafeZoneDTO> getAllSafeZone() {
    List<SafeZone> safeZones = safeZoneService.getSafeZones();
    return safeZoneMapper.toDTO(safeZones);

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
