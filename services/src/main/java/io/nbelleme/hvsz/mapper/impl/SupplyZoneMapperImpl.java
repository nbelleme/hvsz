package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.mapper.api.SupplyZoneMapper;
import io.nbelleme.hvsz.zone.internal.SupplyZone;
import io.nbelleme.hvsz.zone.transfer.SupplyZoneDTO;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by nbelleme on 01/08/2017.
 */
@Component
final class SupplyZoneMapperImpl implements SupplyZoneMapper {

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
  public List<SupplyZoneDTO> toDTO(List<SupplyZone> supplyZones) {
    Objects.requireNonNull(supplyZones);
    return supplyZones.stream()
                      .map(this::toDTO)
                      .collect(Collectors.toList());

  }

  @Override
  public SupplyZone fromDTO(SupplyZoneDTO supplyZoneDTO) {
    Objects.requireNonNull(supplyZoneDTO);
    return mapper.map(supplyZoneDTO, SupplyZone.class);
  }
}
