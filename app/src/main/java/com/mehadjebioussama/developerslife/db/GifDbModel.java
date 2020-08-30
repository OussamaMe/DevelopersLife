package com.mehadjebioussama.developerslife.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "GifsTable")
public class GifDbModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String description;

    private String imageUrl;

    private String gifType;

    public GifDbModel(String description, String imageUrl, String gifType) {
        this.description = description;
        this.imageUrl = imageUrl;
        this.gifType = gifType;
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

    public String getGifType() {
        return gifType;
    }

    public void setId(int id) {
        this.id = id;
    }


}
