package io.nbelleme.persistence.dao.api;

import java.util.Optional;

/**
 * Created by nicolas on 15/07/2017.
 */
public interface AbstractDaoAPI<DTO> {

  /**
   * Transform dto into dpo then insert it in database.
   *
   * @param dto dto to insert
   * @return DTO saved DTO
   */
  DTO save(DTO dto);

  /**
   * Return DTO from database.
   *
   * @return DTO from database
   */
  Optional<DTO> get();

  /**
   * Update DTO in database.
   *
   * @param dto DTO to update
   * @return DTO updated
   */
  DTO update(DTO dto);

  /**
   * Delete DTO in database.
   *
   * @param dto DTO to delete
   * @return DTO deleted
   */
  DTO delete(DTO dto);


}
