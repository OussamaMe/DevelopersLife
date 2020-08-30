package com.mehadjebioussama.developerslife.di;

import com.mehadjebioussama.developerslife.util.factory.GifFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class GifFactoryModule {
    @Provides
    GifFactory provideGifFactory(){
        return new GifFactory();
    }
}
