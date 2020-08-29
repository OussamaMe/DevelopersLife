package com.mehadjebioussama.developerslife;

import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DisposableManager {

    private final String TAG = DisposableManager.class.getSimpleName();
    private CompositeDisposable compositeDisposable;

    public void add(Disposable disposable) {
        Log.e(TAG, "add");
        getCompositeDisposable().add(disposable);
    }

    public void dispose() {
        Log.e(TAG, "dispose");
        getCompositeDisposable().dispose();
    }

    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }
}
