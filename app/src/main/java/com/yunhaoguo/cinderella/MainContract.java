package com.yunhaoguo.cinderella;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella
 * 文件名:     MainContract
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/27 3:37 PM
 * 描述:      TODO
 */


import com.yunhaoguo.cinderella.entity.Machine;
import com.yunhaoguo.cinderella.mvp.MvpContract;

import java.util.List;

public interface MainContract {

    interface View extends MvpContract.View<Presenter> {
        void getMachineStates(List<Machine> machines);
    }

    interface Presenter extends MvpContract.Presenter<View, Model> {
        void getMachineStates(List<Machine> machines);
    }

    interface Model extends MvpContract.Model<Presenter> {
        void fetchMachineStates();
    }
}
