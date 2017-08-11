package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.human.internal.Human;
import io.nbelleme.hvsz.human.transfer.HumanDTO;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
@Component
public class HumanMapper {

  private Mapper mapper;

  /**
   * Constructor.
   *
   * @param mapper mapper
   */
  HumanMapper(Mapper mapper) {
    this.mapper = Objects.requireNonNull(mapper);
  }

  /**
   * Map {@link Human} to {@link HumanDTO}.
   *
   * @param human human object to map
   * @return humanDTO mapped
   */
  public HumanDTO toDTO(Human human) {
    Objects.requireNonNull(human);
    return mapper.map(human, HumanDTO.class);
  }

  /**
   * Map {@link Human} to {@link HumanDTO}.
   *
   * @param humanDTO supplyZone object to unmap
   * @return humanDTO unmapped
   */
  public Human fromDTO(HumanDTO humanDTO) {
    Objects.requireNonNull(humanDTO);
    return mapper.map(humanDTO, Human.class);
  }
}
