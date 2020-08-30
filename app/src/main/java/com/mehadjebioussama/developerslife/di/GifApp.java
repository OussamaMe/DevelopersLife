package com.mehadjebioussama.developerslife.di;

import android.app.Application;

import androidx.annotation.NonNull;

public class GifApp extends Application {
    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .databaseModule(new DatabaseModule())
                .networkModule(new NetworkModule())
                .disposableModule(new DisposableModule())
                .gifFactoryModule(new GifFactoryModule())
                .build();
    }

    @NonNull
    public static AppComponent getAppComponent() {
        return component;
    }
}
