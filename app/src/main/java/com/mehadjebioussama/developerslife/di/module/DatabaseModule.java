package com.mehadjebioussama.developerslife.di.module;

import android.content.Context;

import com.mehadjebioussama.developerslife.db.GifDataBase;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.room.Room;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    GifDataBase provideMoviesDB(@NonNull Context context){
        return Room.databaseBuilder(context, GifDataBase.class, "gif_db")
                .fallbackToDestructiveMigration().build();
    }
}
