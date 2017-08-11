package io.nbelleme.hvsz.mapper.api;

import io.nbelleme.hvsz.zone.internal.SafeZone;
import io.nbelleme.hvsz.zone.transfer.SafeZoneDTO;

import java.util.List;

public interface SafeZoneMapper {
  /**
   * Map {@link SafeZone} to {@link SafeZoneDTO}.
   *
   * @param safeZone safeZone object to map
   * @return safeZoneDTO mapped
   */
  SafeZoneDTO toDTO(SafeZone safeZone);

  /**
   * Map {@link List} of {@link SafeZone} to {@link List} of {@link SafeZoneDTO}.
   *
   * @param safeZones list of safeZone objects to map
   * @return list of safeZoneDTO mapped
   */
  List<SafeZoneDTO> toDTO(List<SafeZone> safeZones);

  /**
   * Unmap {@link SafeZoneDTO} to {@link SafeZone}.
   *
   * @param safeZoneDTO object to unmap
   * @return safeZone unmapped
   */
  SafeZone fromDTO(SafeZoneDTO safeZoneDTO);
}
