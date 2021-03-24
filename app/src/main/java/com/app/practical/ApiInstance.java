package com.app.practical;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiInstance {

    public ApiInstance apiInstance;

    public static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder().baseUrl("https://api.androidhive.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
