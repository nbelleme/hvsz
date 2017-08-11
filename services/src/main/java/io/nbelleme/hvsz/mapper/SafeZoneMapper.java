package io.nbelleme.hvsz.mapper;

import io.nbelleme.hvsz.zone.internal.SafeZone;
import io.nbelleme.hvsz.zone.transfer.SafeZoneDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by nbelleme on 01/08/2017.
 */
public class SafeZoneMapper {

  /**
   * Map {@link SafeZone} to {@link SafeZoneDTO}.
   *
   * @param safeZone safeZone object to map
   * @return safeZoneDTO mapped
   */
  public static SafeZoneDTO toDTO(SafeZone safeZone) {
    Objects.requireNonNull(safeZone);
    return SafeZoneDTO.build()
                      .setId(safeZone.getId())
                      .setCapacity(safeZone.getCapacity())
                      .setDestroyed(safeZone.isDestroyed())
                      .setLevel(safeZone.getLevel())
                      .setName(safeZone.getName());
  }

  /**
   * Map {@link List} of {@link SafeZone} to {@link List} of {@link SafeZoneDTO}.
   *
   * @param safeZones list of safeZone objects to map
   * @return list of safeZoneDTO mapped
   */
  public static List<SafeZoneDTO> toDTO(List<SafeZone> safeZones) {
    Objects.requireNonNull(safeZones);
    return safeZones.stream()
                    .map(SafeZoneMapper::toDTO)
                    .collect(Collectors.toList());
  }

  /**
   * Unmap {@link SafeZoneDTO} to {@link SafeZone}.
   *
   * @param safeZoneDTO object to unmap
   * @return safeZone unmapped
   */
  public static SafeZone fromDTO(SafeZoneDTO safeZoneDTO) {
    Objects.requireNonNull(safeZoneDTO);
    return SafeZone.build()
                   .setId(safeZoneDTO.getId())
                   .setCapacity(safeZoneDTO.getCapacity())
                   .setDestroyed(safeZoneDTO.isDestroyed())
                   .setLevel(safeZoneDTO.getLevel())
                   .setName(safeZoneDTO.getName());

  }


}
