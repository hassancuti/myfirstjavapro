package com.example.imageuplaod.Model;

import javax.persistence.*;

@Entity
@Table(name = "imageupload")
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name;
    String photo;

    public ImageModel() {
    }

    public ImageModel(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
