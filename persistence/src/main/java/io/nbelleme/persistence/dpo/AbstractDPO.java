package io.nbelleme.persistence.dpo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public abstract class AbstractDPO<DTO> {

  private Long lastUpdate;
  private DTO dto;

  public AbstractDPO(DTO dto) {
    super();
    this.dto = dto;
  }

  public AbstractDPO() {
    LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
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
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

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
