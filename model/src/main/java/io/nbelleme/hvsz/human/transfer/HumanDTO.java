package io.nbelleme.hvsz.human.transfer;

import io.nbelleme.hvsz.api.DPO;

import java.io.Serializable;

public class HumanDTO implements Serializable, DPO {

  private static final long serialVersionUID = -302241752023615893L;
  private static final int MAX_RESOURCES = 10;

  private Long id;
  private boolean alive;
  private int token;
  private int nbResources;

  /**
   * Default constructor.
   */
  private HumanDTO() {

  }

  /**
   * Build default HumanDTO object.
   *
   * @return new HumanDTO.
   */
  public static HumanDTO build() {
    return new HumanDTO();
  }

  //CHECKSTYLE_OFF
  public static int getMaxResources() {
    return MAX_RESOURCES;
  }

  public Long getId() {
    return this.id;
  }

  public HumanDTO setId(Long id) {
    this.id = id;
    return this;
  }

  public boolean isAlive() {
    return this.alive;
  }

  public HumanDTO setAlive(boolean alive) {
    this.alive = alive;
    return this;
  }

  public int getNbResources() {
    return this.nbResources;
  }

  public HumanDTO setNbResources(int nbResources) {
    this.nbResources = nbResources;
    return this;
  }

  public int getToken() {
    return token;
  }

  public HumanDTO setToken(int token) {
    this.token = token;
    return this;
  }
  //CHECKSTYLE_ON

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    HumanDTO humanDTO = (HumanDTO) o;

    return getId().equals(humanDTO.getId());
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }
}
