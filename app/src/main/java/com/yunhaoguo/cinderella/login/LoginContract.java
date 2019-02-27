package com.yunhaoguo.cinderella.login;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella.login
 * 文件名:     LoginContract
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/27 5:07 PM
 * 描述:      TODO
 */


import com.yunhaoguo.cinderella.mvp.MvpContract;

public interface LoginContract {

    interface View extends MvpContract.View<Presenter> {

    }

    interface Presenter extends MvpContract.Presenter<View, Model> {

    }

    interface Model extends MvpContract.Model<Presenter> {
        void login();
    }
}
