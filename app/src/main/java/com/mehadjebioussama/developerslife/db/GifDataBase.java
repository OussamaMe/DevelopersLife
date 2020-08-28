package com.mehadjebioussama.developerslife.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GifDbModel.class}, version = 1)
public abstract class GifDataBase extends RoomDatabase {
    public abstract GifDao getGifDao();
}
