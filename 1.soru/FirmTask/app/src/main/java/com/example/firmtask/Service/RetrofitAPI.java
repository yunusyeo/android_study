package com.example.firmtask.Service;

import com.example.firmtask.Model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/")
    Call<Response> request(@Query("t") String searchText,
                           @Query("apikey") String apiKey);
}
