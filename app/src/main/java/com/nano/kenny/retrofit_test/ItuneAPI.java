package com.nano.kenny.retrofit_test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.RestAdapter;
import retrofit.android.MainThreadExecutor;

/**
 * Created by Kenny on 6/24/2015.
 */
public class ItuneAPI {
    public static final String ITUNES_API = "https://itunes.apple.com";
    private final ItuneService mItuneService;

    public ItuneAPI() {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ITUNES_API).build();
        mItuneService = restAdapter.create(ItuneService.class);
    }

    public ItuneService getItuneService() {
        return mItuneService;
    }
}
