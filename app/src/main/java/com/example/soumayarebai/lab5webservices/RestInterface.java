package com.example.soumayarebai.lab5webservices;

import com.example.soumayarebai.lab5webservices.POJO.Model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Soumaya Rebai on 02/05/2017.
 */

public interface RestInterface {
    @GET("weather?APPID=9ee6309d1b72954fb088c6585afeaffc")
    Call<Model> getWeatherReport(@Query("q") String city);

    public static Retrofit RETROFIT=new Retrofit.Builder().baseUrl("http://api.openweathermap.org/data/2.5/").addConverterFactory(GsonConverterFactory.create()).build();

}
