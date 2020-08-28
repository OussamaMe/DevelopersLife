package com.mehadjebioussama.developerslife.network;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GifService {
    @GET("/random?json=true")
    Single<Gif> getGif(@Query("types") String types);

    @GET("/random?json=true")
    Single<Gif> getRandomGif();
}
