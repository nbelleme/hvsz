package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.mapper.api.SupplyZoneMapper;
import io.nbelleme.hvsz.zone.internal.SupplyZone;
import io.nbelleme.hvsz.zone.transfer.SupplyZoneDTO;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
@Component
final class SupplyZoneMapperImpl implements SupplyZoneMapper<SupplyZone, SupplyZoneDTO> {

  private Mapper mapper;

  /**
   * Constructor.
   *
   * @param mapper mapper
   */
  SupplyZoneMapperImpl(Mapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public SupplyZoneDTO toDTO(SupplyZone supplyZone) {
    Objects.requireNonNull(supplyZone);
    return mapper.map(supplyZone, SupplyZoneDTO.class);
  }


  @Override
  public SupplyZone fromDTO(SupplyZoneDTO supplyZoneDTO) {
    Objects.requireNonNull(supplyZoneDTO);
    return mapper.map(supplyZoneDTO, SupplyZone.class);
  }
}
