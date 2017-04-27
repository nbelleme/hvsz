package io.resourcepool.hvsz.persistance.models;


import java.io.Serializable;

public class SupplyZone implements ZoneResource, Serializable {
    private static final String NAME = "supply zone n°";
    private Integer id;
    private int resource;
    private String name;

    public SupplyZone(){
    }

    public SupplyZone(int id, int resource){
        this.id = id;
        this.resource = resource;
        name = NAME + id;
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

        SupplyZone supplyZone = (SupplyZone) o;

        return getId().equals(supplyZone.getId());
    }
}
