package com.example.arthurrecycleview;

public class DataModel {
    String name;
    String description;
    int image;
    int id;

    public DataModel(String name, String description, int image, int id) {
        this.name = name;
        this.description = description;
        this.image = image;  // Correctly assign the integer to 'image'
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
