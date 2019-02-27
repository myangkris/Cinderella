package com.yunhaoguo.cinderella;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella
 * 文件名:     MainPresenter
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/27 3:25 PM
 * 描述:      TODO
 */


import com.yunhaoguo.cinderella.entity.Machine;

import java.util.List;

import cn.jpush.android.api.JPushInterface;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MainContract.Model model;

    @Override
    public void onCreate() {
        this.model = new MainModel();
        this.model.setPresenter(this);

    }

    @Override
    public void onViewAttached(MainContract.View view) {
        this.view = view;
        this.model = new MainModel();
        //this.model.fetchMachineStates();
        setPushService(view);
    }

    @Override
    public void onResume() {
        JPushInterface.onResume((MainActivity)view);
    }

    @Override
    public void onPause() {
        JPushInterface.onPause((MainActivity)view);
    }

    private void setPushService(MainContract.View view) {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(((MainActivity)view).getApplicationContext());
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getMachineStates(List<Machine> machines) {
        if (this.view != null) {
            this.view.getMachineStates(machines);
        }
    }
}
