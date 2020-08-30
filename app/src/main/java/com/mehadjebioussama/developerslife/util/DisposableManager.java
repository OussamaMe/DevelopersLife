package com.mehadjebioussama.developerslife.util;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DisposableManager {
    private CompositeDisposable compositeDisposable;

    public void add(Disposable disposable) {
        getCompositeDisposable().add(disposable);
    }

    public void dispose() {
        getCompositeDisposable().dispose();
    }

    private CompositeDisposable getCompositeDisposable() {
        if (compositeDisposable == null || compositeDisposable.isDisposed()) {
            compositeDisposable = new CompositeDisposable();
        }
        return compositeDisposable;
    }
}
