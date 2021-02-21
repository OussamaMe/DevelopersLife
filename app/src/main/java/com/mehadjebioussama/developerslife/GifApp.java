package com.mehadjebioussama.developerslife;

import android.app.Application;

import com.mehadjebioussama.developerslife.di.component.AppComponent;
import com.mehadjebioussama.developerslife.di.component.DaggerAppComponent;
import com.mehadjebioussama.developerslife.di.module.AppModule;
import com.mehadjebioussama.developerslife.di.module.DatabaseModule;
import com.mehadjebioussama.developerslife.di.module.NetworkModule;

import androidx.annotation.NonNull;

public class GifApp extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .databaseModule(new DatabaseModule())
                .networkModule(new NetworkModule())
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return component;
    }
}
