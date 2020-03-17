package com.example.firmtask.Presenter;

import android.util.Log;

import com.example.firmtask.Util.Constants;
import com.example.firmtask.Activity.MainActivity.MainActivityContract;
import com.example.firmtask.Model.Response;
import com.example.firmtask.Service.RetrofitAPI;
import com.example.firmtask.Service.RetrofitGET;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivityPresenter implements MainActivityContract.Presenter {
    private static final String TAG = MainActivityPresenter.class.getSimpleName();

    private MainActivityContract.View mView;

    @Override
    public void setView(MainActivityContract.View view) {
        this.mView = view;
    }

    @Override
    public void create() {
        mView.bindViews();
    }

    @Override
    public void request(String searchText) {
        mView.showProgress();
        RetrofitAPI retrofitAPI = RetrofitGET.getRetrofit().create(RetrofitAPI.class);
        Call<Response> call = retrofitAPI.request(searchText, Constants.API_KEY);
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                mView.hideProgress();
                mView.response(response.body());
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d(TAG, "Error: " + t);
                mView.hideProgress();
            }
        });
    }
}
