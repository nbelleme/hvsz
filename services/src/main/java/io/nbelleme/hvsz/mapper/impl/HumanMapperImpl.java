package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.human.internal.Human;
import io.nbelleme.hvsz.human.transfer.HumanDTO;
import io.nbelleme.hvsz.mapper.api.HumanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
@Component
public class HumanMapperImpl implements HumanMapper<Human, HumanDTO> {

  private Mapper mapper;

  /**
   * Constructor.
   *
   * @param mapper mapper
   */
  HumanMapperImpl(Mapper mapper) {
    this.mapper = Objects.requireNonNull(mapper);
  }

  @Override
  public HumanDTO toDTO(Human human) {
    Objects.requireNonNull(human);
    return mapper.map(human, HumanDTO.class);
  }

  @Override
  public Human fromDTO(HumanDTO humanDTO) {
    Objects.requireNonNull(humanDTO);
    return mapper.map(humanDTO, Human.class);
  }
}
