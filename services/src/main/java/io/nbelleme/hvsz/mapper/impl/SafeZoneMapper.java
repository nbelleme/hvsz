package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.zone.internal.SafeZone;
import io.nbelleme.hvsz.zone.transfer.SafeZoneDTO;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by nbelleme on 01/08/2017.
 */
@Component
public class SafeZoneMapper {

  private Mapper mapper;

  /**
   * Constructor.
   *
   * @param mapper mapper
   */
  SafeZoneMapper(Mapper mapper) {
    this.mapper = mapper;
  }

  /**
   * Map {@link SafeZone} to {@link SafeZoneDTO}.
   *
   * @param safeZone safeZone object to map
   * @return safeZoneDTO mapped
   */
  public SafeZoneDTO toDTO(SafeZone safeZone) {
    Objects.requireNonNull(safeZone);
    return mapper.map(safeZone, SafeZoneDTO.class);
  }

  /**
   * Map {@link List} of {@link SafeZone} to {@link List} of {@link SafeZoneDTO}.
   *
   * @param safeZones list of safeZone objects to map
   * @return list of safeZoneDTO mapped
   */
  public List<SafeZoneDTO> toDTO(List<SafeZone> safeZones) {
    Objects.requireNonNull(safeZones);
    return safeZones.stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
  }

  /**
   * Unmap {@link SafeZoneDTO} to {@link SafeZone}.
   *
   * @param safeZoneDTO object to unmap
   * @return safeZone unmapped
   */
  public SafeZone fromDTO(SafeZoneDTO safeZoneDTO) {
    Objects.requireNonNull(safeZoneDTO);
    return mapper.map(safeZoneDTO, SafeZone.class);

  }


}
