package com.mehadjebioussama.developerslife.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private GifApp app;

    AppModule(GifApp app) {
        this.app = app;
    }

    @Provides
    Context provideContext() {
        return app;
    }
}
