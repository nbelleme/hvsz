package io.nbelleme.hvsz.zone.mapper;

import io.nbelleme.hvsz.zone.internal.SupplyZone;
import io.nbelleme.hvsz.zone.transfer.SupplyZoneDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by nbelleme on 01/08/2017.
 */
public class SupplyZoneMapper {

  /**
   * Map {@link SupplyZone} to {@link SupplyZoneDTO}.
   *
   * @param supplyZone supplyZone object to map
   * @return safeZoneDTO mapped
   */
  public static SupplyZoneDTO toDTO(SupplyZone supplyZone) {
    Objects.requireNonNull(supplyZone);
    return SupplyZoneDTO.build()
                        .setId(supplyZone.getId())
                        .setCapacity(supplyZone.getCapacity())
                        .setLevel(supplyZone.getLevel())
                        .setName(supplyZone.getName());
  }

  /**
   * Map {@link List} of {@link SupplyZone} to {@link List} of {@link SupplyZoneDTO}.
   *
   * @param supplyZones supplyZones object to map
   * @return safeZoneDTO mapped
   */
  public static List<SupplyZoneDTO> toDTO(List<SupplyZone> supplyZones) {
    Objects.requireNonNull(supplyZones);
    return supplyZones.stream()
                      .map(SupplyZoneMapper::toDTO)
                      .collect(Collectors.toList());

  }

  /**
   * Map {@link SupplyZone} to {@link SupplyZoneDTO}.
   *
   * @param supplyZoneDTO supplyZone object to unmap
   * @return supplyZone unmapped
   */
  public static SupplyZone fromDTO(SupplyZoneDTO supplyZoneDTO) {
    Objects.requireNonNull(supplyZoneDTO);
    return SupplyZone.build()
                     .setId(supplyZoneDTO.getId())
                     .setCapacity(supplyZoneDTO.getCapacity())
                     .setLevel(supplyZoneDTO.getLevel())
                     .setName(supplyZoneDTO.getName());

  }
}
