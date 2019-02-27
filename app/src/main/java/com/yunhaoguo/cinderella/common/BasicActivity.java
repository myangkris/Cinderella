package com.yunhaoguo.cinderella.common;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella.common
 * 文件名:     BasicActivity
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/27 3:24 PM
 * 描述:      TODO
 */


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yunhaoguo.cinderella.MainContract;

public abstract class BasicActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = getPresenter();
        if (presenter != null) {
            presenter.onCreate();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.onViewAttached(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onViewDetached();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }
}
