package io.nbelleme.hvsz.humans;

import java.io.Serializable;

public class Life implements Serializable {

  private static final long serialVersionUID = -302241752023615893L;
  private static final int MAX_RESOURCES = 10;

  private Long id;
  private boolean alive;
  private int token;
  private int nbResources;

  /**
   * Default constructor.
   */
  private Life() {

  }

  /**
   * Build default Life object.
   *
   * @return new Life.
   */
  public static Life build() {
    return new Life();
  }

  //CHECKSTYLE_OFF
  public static int getMaxResources() {
    return MAX_RESOURCES;
  }

  public Long getId() {
    return this.id;
  }

  public Life setId(Long id) {
    this.id = id;
    return this;
  }

  public boolean isAlive() {
    return this.alive;
  }

  public Life setAlive(boolean alive) {
    this.alive = alive;
    return this;
  }

  public int getNbResources() {
    return this.nbResources;
  }

  public Life setNbResources(int nbResources) {
    this.nbResources = nbResources;
    return this;
  }

  public int getToken() {
    return token;
  }

  public Life setToken(int token) {
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

    Life life = (Life) o;

    return getId().equals(life.getId());
  }

  @Override
  public int hashCode() {
    return this.id.hashCode();
  }
}
