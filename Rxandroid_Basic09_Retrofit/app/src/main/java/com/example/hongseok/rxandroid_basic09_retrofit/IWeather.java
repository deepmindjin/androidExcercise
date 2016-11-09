package com.example.hongseok.rxandroid_basic09_retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by HongSeok on 2016-11-04.
 */

public interface IWeather {
    // http://api.openweathermap.org
    // /data/2.5/weather?q=Newyork&APPID=52611ad848eecb8b1ecdf323d61a1d4d
    @GET("/data/2.5/weather")
    Observable<Data> getData(@Query("q") String cityName, @Query("APPID") String key);
}
