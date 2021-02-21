package com.mehadjebioussama.developerslife.mainactivity;

import com.mehadjebioussama.developerslife.repository.MainRepository;
import com.mehadjebioussama.developerslife.util.GifFactory;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainContract.View> implements MainContract.Presenter {

    private final MainRepository repository;
    private final GifFactory gifFactory;
    private final CompositeDisposable compositeDisposable;

    @Inject
    public MainPresenter(MainRepository repository, GifFactory gifFactory) {
        super();
        this.repository = repository;
        this.gifFactory = gifFactory;
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadGif(0);
    }

    @Override
    public void tryAgain(int currentItem) {
        getViewState().hideError();
        loadGif(currentItem);
    }

    private void loadGif(int currentItem) {
        updateViews(currentItem);
        compositeDisposable.add(
                repository.loadGifsFromDb(gifFactory.getGifType(currentItem).getType())
                        .subscribe(gifDbModels -> {
                            if (gifDbModels.size() != 0) {
                                if (gifDbModels.size() > gifFactory.getGifType(currentItem).getCurrentGif()) {
                                    getViewState().showGif(gifDbModels.get(gifFactory.getGifType(currentItem).getCurrentGif()));
                                } else {
                                    getGifFromApi(gifFactory.getGifType(currentItem).getType());
                                }

                            } else {
                                getGifFromApi(gifFactory.getGifType(currentItem).getType());
                            }
                        }, throwable -> getViewState().showError(throwable))
        );
    }

    private void updateViews(int currentItem) {
        getViewState().loadingGif();
        if (gifFactory.getGifType(currentItem).getCurrentGif() == 0) {
            getViewState().disablePreviousButton();
        } else {
            getViewState().enablePreviousButton();
        }
    }

    private void getGifFromApi(String gifType) {
        compositeDisposable.add(
                repository.getGifFromApi(gifType)
                        .subscribe(gifDbModel -> getViewState().showGif(gifDbModel)
                                , throwable -> getViewState().showError(throwable))
        );

    }

    @Override
    public void onNextClick(int currentItem) {
        gifFactory.getGifType(currentItem).incrementCurrentGif();
        loadGif(currentItem);
    }

    @Override
    public void onPreviousClick(int currentItem) {
        if (gifFactory.getGifType(currentItem).getCurrentGif() != 0) {
            if (gifFactory.getGifType(currentItem).getCurrentGif() == 1) {
                getViewState().disablePreviousButton();
            }
            gifFactory.getGifType(currentItem).decrementCurrentGif();
            loadGif(currentItem);
        }
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
