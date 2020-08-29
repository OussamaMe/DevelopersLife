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

    @Insert
    public void insertGif(GifDbModel gif);

    @Query("SELECT * FROM GifsTable WHERE id = (:id)")
    public GifDbModel getGif(int id);

    @Query("DELETE FROM GifsTable")
    public void deleteAll();
}
