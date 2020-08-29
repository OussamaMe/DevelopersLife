package com.mehadjebioussama.developerslife.di;

import android.content.Context;

import com.mehadjebioussama.developerslife.db.GifDataBase;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    GifDataBase provideMoviesDB(@NonNull Context context){
        return Room.databaseBuilder(context, GifDataBase.class, "gif_db")
                .fallbackToDestructiveMigration().build();
    }
}
