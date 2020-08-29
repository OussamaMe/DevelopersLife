package com.mehadjebioussama.developerslife.di;

import com.mehadjebioussama.developerslife.mainactivity.MainRepository;

import androidx.annotation.NonNull;
import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class, DatabaseModule.class, DisposableModule.class})
public interface AppComponent {
    void injectMainRepository(@NonNull MainRepository repository);
}
