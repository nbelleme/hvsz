package io.nbelleme.hvsz.mapper.api;

import io.nbelleme.hvsz.zone.internal.SupplyZone;
import io.nbelleme.hvsz.zone.transfer.SupplyZoneDTO;

import java.util.List;

public interface SupplyZoneMapper {
  /**
   * Map {@link SupplyZone} to {@link SupplyZoneDTO}.
   *
   * @param supplyZone supplyZone object to map
   * @return safeZoneDTO mapped
   */
  SupplyZoneDTO toDTO(SupplyZone supplyZone);

  /**
   * Map {@link List} of {@link SupplyZone} to {@link List} of {@link SupplyZoneDTO}.
   *
   * @param supplyZones supplyZones object to map
   * @return safeZoneDTO mapped
   */
  List<SupplyZoneDTO> toDTO(List<SupplyZone> supplyZones);

  /**
   * Map {@link SupplyZone} to {@link SupplyZoneDTO}.
   *
   * @param supplyZoneDTO supplyZone object to unmap
   * @return supplyZone unmapped
   */
  SupplyZone fromDTO(SupplyZoneDTO supplyZoneDTO);
}
