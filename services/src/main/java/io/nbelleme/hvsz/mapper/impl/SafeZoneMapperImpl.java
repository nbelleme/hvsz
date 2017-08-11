package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.mapper.api.SafeZoneMapper;
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
public class SafeZoneMapperImpl implements SafeZoneMapper {

  private Mapper mapper;

  /**
   * Constructor.
   *
   * @param mapper mapper
   */
  SafeZoneMapperImpl(Mapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public SafeZoneDTO toDTO(SafeZone safeZone) {
    Objects.requireNonNull(safeZone);
    return mapper.map(safeZone, SafeZoneDTO.class);
  }

  @Override
  public List<SafeZoneDTO> toDTO(List<SafeZone> safeZones) {
    Objects.requireNonNull(safeZones);
    return safeZones.stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
  }

  @Override
  public SafeZone fromDTO(SafeZoneDTO safeZoneDTO) {
    Objects.requireNonNull(safeZoneDTO);
    return mapper.map(safeZoneDTO, SafeZone.class);

  }


}
