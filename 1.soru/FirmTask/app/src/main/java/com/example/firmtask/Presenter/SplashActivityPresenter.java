package com.example.firmtask.Presenter;

import com.example.firmtask.Activity.SplashActivity.SplashActivityContract;
import com.example.firmtask.Util.HandlerProcess.HandlerCallback;
import com.example.firmtask.Util.HandlerProcess.HandlerManagment;

public class SplashActivityPresenter implements SplashActivityContract.Presenter {

    private SplashActivityContract.View mView;

    @Override
    public void setView(SplashActivityContract.View view) {
        this.mView = view;
    }

    @Override
    public void create() {
        mView.bindViews();
        mView.showProgress();
        new HandlerManagment().postDelayedHandler(new HandlerCallback() {
            @Override
            public void onCompleted() {
                mView.hideProgress();
                mView.finishActivity();
            }
        }, HandlerManagment.DEFAULT_HANDLER_DURATION);
    }
}
