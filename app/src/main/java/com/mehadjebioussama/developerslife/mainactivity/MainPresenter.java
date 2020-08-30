package com.mehadjebioussama.developerslife.mainactivity;

import com.mehadjebioussama.developerslife.db.GifDbModel;
import com.mehadjebioussama.developerslife.util.EspressoTestingIdlingResource;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainContract.View> implements MainContract.Presenter{
    private MainRepository repository;

    public MainPresenter(MainRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        repository.loadGif(callback, 0);
       // Необходимо раскоментить для UI теста
//        EspressoTestingIdlingResource.increment();
    }

    @Override
    public void onNextClick(int currentItem) {
        repository.onNextClick(callback, currentItem);
        // Необходимо раскоментить для UI теста
//        EspressoTestingIdlingResource.increment();
    }

    @Override
    public void onPreviousClick(int currentItem) {
        repository.onPreviousClick(callback, currentItem);
        // Необходимо раскоментить для UI теста
//        EspressoTestingIdlingResource.increment();
    }

    @Override
    public void tryAgain(int currentItem) {
        getViewState().hideError();
        repository.loadGif(callback, currentItem);
        // Необходимо раскоментить для UI теста
//        EspressoTestingIdlingResource.increment();
    }

    @Override
    public void onActivityDestroy() {
        repository.dispose();
    }

    private final MainContract.OnResponseCallback callback = new MainContract.OnResponseCallback() {
        @Override
        public void showData(GifDbModel gifDbModel) {
            getViewState().showGif(gifDbModel);
            // Необходимо раскоментить для UI теста
//            EspressoTestingIdlingResource.decrement();
        }

        @Override
        public void showError(Throwable throwable) {
            getViewState().showError(throwable);
            // Необходимо раскоментить для UI теста
//            EspressoTestingIdlingResource.decrement();
        }

        @Override
        public void disablePreviousButton() {
            getViewState().disablePreviousButton();
        }

        @Override
        public void showProgress() {
            getViewState().loadingGif();
        }

        @Override
        public void enablePreviousButton() {
            getViewState().enablePreviousButton();
        }
    };
}
