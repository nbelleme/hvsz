package io.resourcepool.hvsz.persistance.models;

import java.io.Serializable;

/**
 * Created by root on 26/04/17.
 */
public class SafeZone implements ZoneResource, Serializable {
    private static final String NAME = "safe zone n°";
    private Integer id;
    private int resource;
    private int maxResource;
    private String name;

    public SafeZone(){
    }

    public SafeZone(int id, int resource, int maxResource){
        this.id = id;
        this.resource = resource;
        this.maxResource = maxResource;
        name = NAME + id;
    }

    public static String getNAME() {
        return SafeZone.NAME;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getMaxResource() {
        return this.maxResource;
    }

    public void setMaxResource(int maxResource) {
        this.maxResource = maxResource;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int getResource() {
        return resource;
    }

    @Override
    public String getName() {
        return name;
    }

    public int drop(int quantite) {
        resource += quantite;
        if (resource > maxResource) {
            int over = resource - maxResource;
            resource = maxResource;
            return quantite - over;
        }
        return quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SafeZone supplyZone = (SafeZone) o;

        return getId().equals(supplyZone.getId());
    }
}
