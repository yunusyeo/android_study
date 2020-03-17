package com.example.firmtask.Service;

import com.example.firmtask.Util.Constants;
import com.example.firmtask.Util.Util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitGET {
    private static Retrofit retrofit = null;
    private static String Base_Url= Constants.URL;

    public static Retrofit getRetrofit() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .client(Util.getTimeOut())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        }
        return retrofit;
    }
}
