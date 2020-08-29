package com.mehadjebioussama.developerslife.mainactivity;

import com.mehadjebioussama.developerslife.DisposableManager;
import com.mehadjebioussama.developerslife.db.GifDataBase;
import com.mehadjebioussama.developerslife.network.GifService;

import javax.inject.Inject;

public class MainRepository {
    @Inject
    GifService gifService;

    @Inject
    GifDataBase gifDataBase;

    @Inject
    DisposableManager manager;
}
