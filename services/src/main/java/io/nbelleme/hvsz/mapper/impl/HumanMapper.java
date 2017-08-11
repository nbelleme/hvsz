package io.nbelleme.hvsz.mapper.impl;

import io.nbelleme.hvsz.human.internal.Human;
import io.nbelleme.hvsz.human.transfer.HumanDTO;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
public class HumanMapper {

  /**
   * Map {@link Human} to {@link HumanDTO}.
   *
   * @param human human object to map
   * @return humanDTO mapped
   */
  public static HumanDTO toDTO(Human human) {
    Objects.requireNonNull(human);
    return HumanDTO.build()
                   .setId(human.getId());
  }

  /**
   * Map {@link Human} to {@link HumanDTO}.
   *
   * @param humanDTO supplyZone object to unmap
   * @return humanDTO unmapped
   */
  public static Human fromDTO(HumanDTO humanDTO) {
    Objects.requireNonNull(humanDTO);
    return Human.build()
                .setId(humanDTO.getId());
  }
}
