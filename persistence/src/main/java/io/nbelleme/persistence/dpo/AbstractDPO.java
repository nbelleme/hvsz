package io.nbelleme.persistence.dpo;

import org.mongojack.ObjectId;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


public abstract class AbstractDPO<DTO> {

  @ObjectId
  private String id;
  private Long lastUpdate;
  private DTO dto;

  /**
   * Constructor.
   *
   * @param dto dto
   */
  public AbstractDPO(DTO dto) {
    this();
    this.dto = dto;
  }

  /**
   * Default constructor.
   */
  public AbstractDPO() {
    this.lastUpdate = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
  }

  /**
   * Constructor.
   *
   * @param id         id
   * @param lastUpdate lastUpdate
   * @param dto        dto
   */
  public AbstractDPO(String id, Long lastUpdate, DTO dto) {
    this.id = id;
    this.lastUpdate = lastUpdate;
    this.dto = dto;
  }

  public Long getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Long lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public DTO getDto() {
    return dto;
  }

  public void setDto(DTO dto) {
    this.dto = dto;
  }

  //CHECKSTYLE_OFF

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AbstractDPO<?> that = (AbstractDPO<?>) o;

    return (lastUpdate != null ? lastUpdate.equals(that.lastUpdate) : that.lastUpdate == null) && (dto != null ? dto.equals(that.dto) : that.dto == null);
  }

  @Override
  public int hashCode() {
    int result = lastUpdate != null ? lastUpdate.hashCode() : 0;
    result = 31 * result + (dto != null ? dto.hashCode() : 0);
    return result;
  }


  //CHECKSTYLE_ON
}
