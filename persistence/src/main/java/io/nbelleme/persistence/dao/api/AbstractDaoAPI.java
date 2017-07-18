package io.nbelleme.persistence.dao.api;

/**
 * Created by nicolas on 15/07/2017.
 */
public interface AbstractDaoAPI<DTO> {

  /**
   * Transform dto into dpo then insert it in database.
   *
   * @param dto dto to insert
   */
  void save(DTO dto);

  /**
   * Return DTO from database.
   * @return DTO from database
   */
  DTO get();


}
