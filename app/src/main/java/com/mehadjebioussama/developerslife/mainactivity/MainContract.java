package com.mehadjebioussama.developerslife.mainactivity;

import com.mehadjebioussama.developerslife.db.GifDbModel;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

public interface MainContract {
    @AddToEndSingle
    interface View extends MvpView {

        void loadingGif();

        void showGif(GifDbModel gifDbModel);

        void showError(Throwable throwable);

        void disablePreviousButton();

        void hideError();

        void enablePreviousButton();
    }

    interface Presenter {

        void onNextClick(int currentItem);

        void onPreviousClick(int currentItem);

        void tryAgain(int currentItem);

        void onActivityDestroy();
    }

    interface OnResponseCallback{

        void showData(GifDbModel gifDbModel);

        void showError(Throwable throwable);

        void disablePreviousButton();

        void showProgress();

        void enablePreviousButton();
    }
}
