package com.mehadjebioussama.developerslife.mainactivity;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainContract.View> implements MainContract.Presenter{
    private MainRepository repository;

    public MainPresenter(MainRepository repository) {
        super();
        this.repository = repository;
    }
}
