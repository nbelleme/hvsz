package io.nbelleme.hvsz.human.mapper;

import io.nbelleme.hvsz.human.internal.Human;
import io.nbelleme.hvsz.human.transfer.HumanDTO;

import java.util.Objects;

/**
 * Created by nbelleme on 01/08/2017.
 */
public class HumanMapper {

  public static HumanDTO toDTO(Human human) {
    Objects.requireNonNull(human);
    return HumanDTO.build()
                   .setId(human.getId());
  }

  public static Human fromDTO(HumanDTO humanDTO) {
    Objects.requireNonNull(humanDTO);
    return Human.build()
                .setId(humanDTO.getId());
  }
}
