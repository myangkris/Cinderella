package com.yunhaoguo.cinderella.mvp;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella.mvp
 * 文件名:     MvpContract
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/27 3:07 PM
 * 描述:      TODO
 */


public interface MvpContract {

    interface View<P extends Presenter> {
        P getPresenter();
    }

    interface Presenter<V extends View, M extends Model> {

        void onCreate();

        void onViewAttached(V view);

        void onResume();

        void onPause();

        void onViewDetached();

        void onDestroy();

    }

    interface Model<P extends Presenter> {
        void setPresenter(P presenter);
    }

}
