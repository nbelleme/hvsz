package io.nbelleme.hvsz.humans;

import io.nbelleme.hvsz.api.DPO;

import java.io.Serializable;

public class Human implements Serializable, DPO {

  private static final long serialVersionUID = -302241752023615893L;
  private static final int MAX_RESOURCES = 10;

  private Long id;
  private boolean alive;
  private int token;
  private int nbResources;

  /**
   * Default constructor.
   */
  private Human() {

  }

  /**
   * Build default Human object.
   *
   * @return new Human.
   */
  public static Human build() {
    return new Human();
  }

  //CHECKSTYLE_OFF
  public static int getMaxResources() {
    return MAX_RESOURCES;
  }

  public Long getId() {
    return this.id;
  }

  public Human setId(Long id) {
    this.id = id;
    return this;
  }

  public boolean isAlive() {
    return this.alive;
  }

  public Human setAlive(boolean alive) {
    this.alive = alive;
    return this;
  }

  public int getNbResources() {
    return this.nbResources;
  }

  public Human setNbResources(int nbResources) {
    this.nbResources = nbResources;
    return this;
  }

  public int getToken() {
    return token;
  }

  public Human setToken(int token) {
    this.token = token;
    return this;
  }

  //CHECKSTYLE_ON

  /**
   * refill res.
   *
   * @param qte qte to refill
   * @return dropped qte
   */
  public int dropResources(int qte) {
    if (nbResources - qte >= 0) {
      this.nbResources -= qte;
      return qte;
    } else {
      int removed = this.nbResources;
      this.nbResources = 0;
      return removed;
    }
  }

  /**
   * refill res.
   *
   * @return dropped qte
   */
  public int dropAllResources() {
    int removed = this.nbResources;
    this.nbResources = 0;
    return removed;
  }

  /**
   * add a resource to inventory.
   *
   * @param qte qte to add
   * @return qte added
   */
  public boolean addResource(int qte) {
    if (!alive) {
      return false;
    }

    if (this.nbResources + qte <= MAX_RESOURCES) {
      this.nbResources += qte;
      return true;
    } else {
      this.nbResources = MAX_RESOURCES;
      return false;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Human human = (Human) o;

    return getId().equals(human.getId());
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }
}
