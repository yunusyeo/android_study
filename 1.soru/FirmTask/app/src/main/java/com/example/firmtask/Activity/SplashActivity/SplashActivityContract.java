package com.example.firmtask.Activity.SplashActivity;

import com.example.firmtask.Presenter.BasePresenter;
import com.example.firmtask.View.BaseView;

public interface SplashActivityContract {
    interface View extends BaseView {
        void finishActivity();
    }

    interface Presenter extends BasePresenter<View> {
    }

}
