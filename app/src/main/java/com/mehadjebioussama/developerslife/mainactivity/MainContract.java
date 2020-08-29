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
    }

    interface Presenter {

        void onNextClick();

        void onPreviousClick();

        void tryAgain();

        void onActivityDestroy();
    }

    interface OnResponseCallback{

        void showData(GifDbModel gifDbModel);

        void showError(Throwable throwable);

        void disablePreviousButton();

        void showProgress();
    }
}
