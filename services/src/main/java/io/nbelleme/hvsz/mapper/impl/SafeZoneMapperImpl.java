package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.mapper.api.SafeZoneMapper;
import io.nbelleme.hvsz.zone.internal.SafeZone;
import io.nbelleme.hvsz.zone.transfer.SafeZoneDTO;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
@Component
final class SafeZoneMapperImpl implements SafeZoneMapper<SafeZone, SafeZoneDTO> {

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
  public SafeZone fromDTO(SafeZoneDTO safeZoneDTO) {
    Objects.requireNonNull(safeZoneDTO);
    return mapper.map(safeZoneDTO, SafeZone.class);

  }


}
