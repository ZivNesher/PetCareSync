package com.example.petcare_shared.models;

public class Pet {
    private String id;
    private String name;
    private String species;
    private String ownerId;

    public Pet() {}

    public Pet(String id, String name, String species, String ownerId) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.ownerId = ownerId;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public String getOwnerId() { return ownerId; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSpecies(String species) { this.species = species; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
}
