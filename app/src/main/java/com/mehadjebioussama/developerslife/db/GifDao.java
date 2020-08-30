package com.mehadjebioussama.developerslife.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface GifDao {
    @Query("SELECT * FROM GifsTable")
    public Single<List<GifDbModel>> getGifs();

    @Query("SELECT * FROM GifsTable WHERE gifType = (:gifType)")
    public Single<List<GifDbModel>> getGifsWithType(String gifType);

    @Insert
    public void insertGif(GifDbModel gif);

    @Query("DELETE FROM GifsTable")
    public void deleteAll();
}
