package com.mehadjebioussama.developerslife.di;

import com.mehadjebioussama.developerslife.util.DisposableManager;

import dagger.Module;
import dagger.Provides;

@Module
public class DisposableModule {
    @Provides
    DisposableManager provideDisposableManager(){
        return new DisposableManager();
    }
}
