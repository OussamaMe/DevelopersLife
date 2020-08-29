package com.mehadjebioussama.developerslife.di;

import com.mehadjebioussama.developerslife.network.GifService;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    Retrofit provideRetrofit() {
        return
                new Retrofit.Builder()
                        .baseUrl("https://developerslife.ru")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
    }

    @Provides
    GifService provideGifsService(@NonNull Retrofit retrofit) {
        return retrofit.create(GifService.class);
    }
}
