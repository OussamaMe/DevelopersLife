package com.mehadjebioussama.developerslife.mainactivity;

import com.mehadjebioussama.developerslife.DisposableManager;
import com.mehadjebioussama.developerslife.db.GifDataBase;
import com.mehadjebioussama.developerslife.db.GifDbModel;
import com.mehadjebioussama.developerslife.di.GifApp;
import com.mehadjebioussama.developerslife.network.GifService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {
    @Inject
    GifService gifService;

    @Inject
    GifDataBase gifDataBase;

    @Inject
    DisposableManager manager;

    private int currentGif = 0;

    public MainRepository() {
        GifApp.getAppComponent().injectMainRepository(this);
    }

    public void loadGif(MainContract.OnResponseCallback callback) {
        callback.showProgress();
        manager.add(
                gifDataBase.getGifDao().getGifs()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(gifDbModels -> {
                            if (gifDbModels.size() != 0) {
                                if (gifDbModels.size() > currentGif) {
                                    callback.showData(gifDbModels.get(currentGif));
                                } else {
                                    getGifFromApi(callback);
                                }

                            } else {
                                getGifFromApi(callback);
                            }
                        }, callback::showError)
        );
    }

    private void getGifFromApi(MainContract.OnResponseCallback callback) {
        manager.add(
                gifService.getRandomGif()
                        .subscribeOn(Schedulers.io())
                        .map(gif -> new GifDbModel(gif.getDescription(), gif.getGifURL()))
                        .doOnSuccess(this::handelDoOnSuccess)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(callback::showData
                                , callback::showError)
        );
    }

    private void handelDoOnSuccess(GifDbModel gifDbModel) {
        gifDataBase.getGifDao().insertGif(gifDbModel);
    }

    public void onNextClick(MainContract.OnResponseCallback callback) {
        currentGif++;
        loadGif(callback);
    }

    public void onPreviousClick(MainContract.OnResponseCallback callback) {
        if (currentGif != 0) {
            if (currentGif == 1) {
                callback.disablePreviousButton();
            }
            currentGif--;
            loadGif(callback);
        }
    }

    public void dispose() {
        manager.dispose();
    }
}
