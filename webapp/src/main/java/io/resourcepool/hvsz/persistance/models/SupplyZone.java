package io.resourcepool.hvsz.persistance.models;


import java.io.Serializable;

public class SupplyZone implements ZoneResource, Serializable {
    private static final String NAME = "supply zone n°";
    private static final String TYPE = "supplyzone";
    private Integer id;
    private int resource;
    private String name;
    private String type;

    /**
     * SupplyZone constructor.
     */
    public SupplyZone() {
    }

    /**
     * SupplyZone constructor.
     *
     * @param id       int
     * @param resource int
     */
    public SupplyZone(int id, int resource) {
        this.id = id;
        this.resource = resource;
        name = NAME + id;
        type = TYPE;
    }

    public String getType() {
        return type;
    }


    public static String getNAME() {
        return SupplyZone.NAME;
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
     * Get resource with an amount.
     *
     * @param quantite int
     * @return int (return the successfull getted amount)
     */
    public int getResource(int quantite) {
        resource -= quantite;
        if (resource < 0) {
            int over = -resource;
            resource = 0;
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

        SupplyZone that = (SupplyZone) o;

        return resource == that.resource && (id != null ? id.equals(that.id) : that.id == null) && (name != null ? name.equals(that.name) : that.name == null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + resource;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
