package com.mehadjebioussama.developerslife.mainactivity;

import com.mehadjebioussama.developerslife.util.DisposableManager;
import com.mehadjebioussama.developerslife.db.GifDataBase;
import com.mehadjebioussama.developerslife.db.GifDbModel;
import com.mehadjebioussama.developerslife.di.GifApp;
import com.mehadjebioussama.developerslife.util.factory.GifFactory;
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

    @Inject
    GifFactory gifFactory;

    public MainRepository() {
        GifApp.getAppComponent().injectMainRepository(this);
    }

    public void loadGif(MainContract.OnResponseCallback callback, int currentItem) {
        callback.showProgress();
        if(gifFactory.getGifType(currentItem).getCurrentGif()==0){
            callback.disablePreviousButton();
        }else {
            callback.enablePreviousButton();
        }
        manager.add(
//                gifDataBase.getGifDao().getGifs()
                gifDataBase.getGifDao().getGifsWithType(gifFactory.getGifType(currentItem).getType())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(gifDbModels -> {
                            if (gifDbModels.size() != 0) {
                                if (gifDbModels.size() > gifFactory.getGifType(currentItem).getCurrentGif()) {
                                    callback.showData(gifDbModels.get(gifFactory.getGifType(currentItem).getCurrentGif()));
                                } else {
                                    getGifFromApi(callback, gifFactory.getGifType(currentItem).getType());
                                }

                            } else {
                                getGifFromApi(callback, gifFactory.getGifType(currentItem).getType());
                            }
                        }, callback::showError)
        );
    }

    private void getGifFromApi(MainContract.OnResponseCallback callback, String gifType) {
        manager.add(
//                gifService.getRandomGif()
                gifService.getGif(gifType)
                        .subscribeOn(Schedulers.io())
                        .map(gif -> new GifDbModel(gif.getDescription(), gif.getGifURL(), gifType))
                        .doOnSuccess(this::handelDoOnSuccess)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(callback::showData
                                , callback::showError)
        );
    }

    private void handelDoOnSuccess(GifDbModel gifDbModel) {
        gifDataBase.getGifDao().insertGif(gifDbModel);
    }

    public void onNextClick(MainContract.OnResponseCallback callback, int currentItem) {
        gifFactory.getGifType(currentItem).incrementCurrentGif();
        loadGif(callback, currentItem);
    }

    public void onPreviousClick(MainContract.OnResponseCallback callback, int currentItem) {
        if (gifFactory.getGifType(currentItem).getCurrentGif() != 0) {
            if (gifFactory.getGifType(currentItem).getCurrentGif() == 1) {
                callback.disablePreviousButton();
            }
            gifFactory.getGifType(currentItem).decrementCurrentGif();
            loadGif(callback, currentItem);
        }
    }

    public void dispose() {
        manager.dispose();
    }
}
