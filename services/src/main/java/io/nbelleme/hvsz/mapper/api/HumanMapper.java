package io.nbelleme.hvsz.mapper.api;

import io.nbelleme.hvsz.human.internal.Human;
import io.nbelleme.hvsz.human.transfer.HumanDTO;

public interface HumanMapper {
  /**
   * Map {@link Human} to {@link HumanDTO}.
   *
   * @param human human object to map
   * @return humanDTO mapped
   */
  HumanDTO toDTO(Human human);

  /**
   * Map {@link Human} to {@link HumanDTO}.
   *
   * @param humanDTO supplyZone object to unmap
   * @return humanDTO unmapped
   */
  Human fromDTO(HumanDTO humanDTO);
}
