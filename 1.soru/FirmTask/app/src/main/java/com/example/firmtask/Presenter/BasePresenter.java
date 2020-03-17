package com.example.firmtask.Presenter;

import com.example.firmtask.View.BaseView;

public interface BasePresenter<T extends BaseView> {
    void setView(T view);
    void create();
}
