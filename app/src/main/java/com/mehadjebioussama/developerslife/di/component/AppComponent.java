package com.mehadjebioussama.developerslife.di.component;

import com.mehadjebioussama.developerslife.di.module.AppModule;
import com.mehadjebioussama.developerslife.di.module.DatabaseModule;
import com.mehadjebioussama.developerslife.di.module.NetworkModule;
import com.mehadjebioussama.developerslife.mainactivity.MainActivity;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Component;

@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        DatabaseModule.class})
@Singleton
public interface AppComponent {
    void injectMainActivity(@NonNull MainActivity mainActivity);
}
