package com.mehadjebioussama.developerslife.di.module;

import com.mehadjebioussama.developerslife.network.GifService;

import javax.inject.Singleton;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return
                new Retrofit.Builder()
                        .baseUrl("https://developerslife.ru")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
    }

    @Provides
    @Singleton
    GifService provideGifsService(@NonNull Retrofit retrofit) {
        return retrofit.create(GifService.class);
    }
}
