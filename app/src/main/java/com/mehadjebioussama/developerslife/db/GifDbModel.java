package com.mehadjebioussama.developerslife.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "GifsTable")
public class GifDbModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String description;

    private String imageUrl;

    public GifDbModel(String description, String imageUrl) {
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }
}
