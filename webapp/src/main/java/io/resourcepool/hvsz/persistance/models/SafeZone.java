package io.resourcepool.hvsz.persistance.models;

import java.io.Serializable;

public class SafeZone implements ZoneResource, Serializable {
    private static final String NAME = "safe zone n°";
    private static final String TYPE = "safezone";
    private Integer id;
    private int resource;
    private int maxResource;
    private String name;
    private String type;

    /**
     * SafeZone Empty constructor.
     */
    public SafeZone() {
    }

    /**
     * SafeZone constructor.
     * @param id the id of the SafeZone
     * @param resource the amount of Resource at the start of the game
     * @param maxResource the max of Resource the zone can hold.
     */
    public SafeZone(int id, int resource, int maxResource) {
        this.id = id;
        this.resource = resource;
        this.maxResource = maxResource;
        name = NAME + id;
        type = TYPE;
    }


    public String getType() {
        return type;
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

    /**
     * Drop an amount of resource.
     * @param quantite amount of resource we want to drop
     * @return how many resource we have realy drop
     */
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
        SafeZone safeZone = (SafeZone) o;
        return resource == safeZone.resource && maxResource == safeZone.maxResource && (id != null ? id.equals(safeZone.id) : safeZone.id == null) && (name != null ? name.equals(safeZone.name) : safeZone.name == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + resource;
        result = 31 * result + maxResource;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
