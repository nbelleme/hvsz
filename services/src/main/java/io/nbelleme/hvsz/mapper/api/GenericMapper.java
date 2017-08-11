package io.nbelleme.hvsz.mapper.api;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface GenericMapper<DPO, DTO> {

  /**
   * Map persistence object to transfer object.
   *
   * @param item object to map
   * @return transfer object  mapped
   */
  DTO toDTO(DPO item);

  /**
   * Map transfer object to persistence object.
   *
   * @param itemDTO object to unmap
   * @return persistence object unmapped
   */
  DPO fromDTO(DTO itemDTO);

  /**
   * Map {@link List} of persistence objects to {@link List} of transfer objects.
   *
   * @param dpoList list of object to map
   * @return list of object mapped
   */
  default List<DTO> toDTO(List<DPO> dpoList) {
    Objects.requireNonNull(dpoList);

    return dpoList.stream()
                  .map(this::toDTO)
                  .collect(Collectors.toList());
  }

  /**
   * Map {@link List} of transfer objects to {@link List} of persistence objects.
   *
   * @param dtoList list of object to map
   * @return list of object mapped
   */
  default List<DPO> fromDTO(List<DTO> dtoList) {
    Objects.requireNonNull(dtoList);

    return dtoList.stream()
                  .map(this::fromDTO)
                  .collect(Collectors.toList());
  }
}
