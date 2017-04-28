package io.resourcepool.hvsz.persistance.models;

/**
 * Created by ebiz on 28/04/17.
 */
public class Life {

    private Integer id;
    private boolean alive;
    private int nbResources;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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
}
