package com.mehadjebioussama.developerslife.repository;

import com.mehadjebioussama.developerslife.db.GifDataBase;
import com.mehadjebioussama.developerslife.db.GifDbModel;
import com.mehadjebioussama.developerslife.network.GifService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    private final GifService gifService;
    private final GifDataBase gifDataBase;

    @Inject
    public MainRepository(GifService gifService,
                          GifDataBase gifDataBase) {
        this.gifService = gifService;
        this.gifDataBase = gifDataBase;
    }

    public Single<List<GifDbModel>> loadGifsFromDb(String gifType) {
        return gifDataBase.getGifDao().getGifsWithType(gifType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<GifDbModel> getGifFromApi(String gifType) {
        return gifService.getGif(gifType)
                .subscribeOn(Schedulers.io())
                .map(gif -> new GifDbModel(gif.getDescription(), gif.getGifURL(), gifType))
                .doOnSuccess(this::handelDoOnSuccess)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private void handelDoOnSuccess(GifDbModel gifDbModel) {
        gifDataBase.getGifDao().insertGif(gifDbModel);
    }

}
