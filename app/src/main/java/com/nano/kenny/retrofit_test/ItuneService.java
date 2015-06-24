package com.nano.kenny.retrofit_test;

/**
 * Created by Kenny on 6/24/2015.
 */
import java.util.Map;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import model.*;
import retrofit.http.QueryMap;

public interface ItuneService {
    @GET("/lookup")
    public void getArtistTopTenTracks(@QueryMap Map<String, String> options,
                                      Callback<Itunes> response);
}
