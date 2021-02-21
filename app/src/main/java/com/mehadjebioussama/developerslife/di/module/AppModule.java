package com.mehadjebioussama.developerslife.di.module;

import android.content.Context;

import com.mehadjebioussama.developerslife.GifApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final GifApp app;

    public AppModule(GifApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return app;
    }
}
