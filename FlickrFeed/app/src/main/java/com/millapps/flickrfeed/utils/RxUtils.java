package com.millapps.flickrfeed.utils;

import android.util.Log;

import com.millapps.flickrfeed.BuildConfig;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class RxUtils {
    private static final String TAG = RxUtils.class.getName();

    public static <T> ObservableTransformer<T, T> applyIOSchedulers(Scheduler io, Scheduler ui) {
        return observable -> observable.subscribeOn(io)
                .observeOn(ui);
    }

    /**
     * One important design requirement for 2.x is that no Throwable errors should be swallowed.To avoid this, we set a no-op handler
     * as suggested at https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0#error-handling.
     * <p>
     */
    public static void initRxJava2ErrorHandler() {
        RxJavaPlugins.setErrorHandler(throwable -> {
            if (BuildConfig.DEBUG) Log.d(TAG, Log.getStackTraceString(throwable));
        });
    }

}
