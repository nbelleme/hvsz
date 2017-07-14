package io.nbelleme.hvsz.humans;

import java.io.Serializable;

public class Life implements Serializable {

  private static final long serialVersionUID = -302241752023615893L;
  private static final int MAX_RESOURCES = 10;

  private Long id;
  private boolean alive;
  private int token;
  private int nbResources;

  public static int getMaxResources() {
    return MAX_RESOURCES;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isAlive() {
    return this.alive;
  }

  public void setAlive(boolean alive) {
    this.alive = alive;
  }

  public int getNbResources() {
    return this.nbResources;
  }

  public void setNbResources(int nbResources) {
    this.nbResources = nbResources;
  }

  public int getToken() {
    return token;
  }

  public void setToken(int token) {
    this.token = token;
  }

  //TODO : use service

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

  //TODO : use service

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

  //TODO : use service

  /**
   * add a resource to inventory.
   *
   * @param qte qte to add
   * @return qte added
   */
  public int addResource(int qte) {
    if (!alive) {
      return 0;
    }
    if (this.nbResources + qte <= MAX_RESOURCES) {
      this.nbResources += qte;
      return qte;
    } else {
      int addedRes = MAX_RESOURCES - this.nbResources;
      this.nbResources = MAX_RESOURCES;
      return addedRes;
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

    Life life = (Life) o;

    return getId().equals(life.getId());
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }
}
