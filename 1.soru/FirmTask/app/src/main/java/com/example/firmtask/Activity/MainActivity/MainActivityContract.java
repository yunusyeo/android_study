package com.example.firmtask.Activity.MainActivity;

import com.example.firmtask.Model.Response;
import com.example.firmtask.Presenter.BasePresenter;
import com.example.firmtask.View.BaseView;

public interface MainActivityContract {
    interface View extends BaseView {
        void response(Response response);
    }

    interface Presenter extends BasePresenter<View> {
        void request(String searchText);
    }
}
