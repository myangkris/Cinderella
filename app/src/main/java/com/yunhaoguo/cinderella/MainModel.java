package com.yunhaoguo.cinderella;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella
 * 文件名:     MainModel
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/27 3:32 PM
 * 描述:      TODO
 */


import com.yunhaoguo.cinderella.entity.Machine;
import com.yunhaoguo.cinderella.retrofit.MachinesRequestApi;
import com.yunhaoguo.cinderella.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainModel implements MainContract.Model {

    private MainContract.Presenter presenter;

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void fetchMachineStates() {
        //get data
        MachinesRequestApi machinesRequestApi = RetrofitClient.getInstance().create(MachinesRequestApi.class);
        int userId = 1;
        int machineId = 2;
        int duration = 3;
        Call<List<Machine>> call = machinesRequestApi.getMachinesStates(userId, machineId, duration);
        call.enqueue(new Callback<List<Machine>>() {
            @Override
            public void onResponse(Call<List<Machine>> call, Response<List<Machine>> response) {
                if (response.isSuccessful()) {
                    List<Machine> machines = response.body();
                    presenter.getMachineStates(machines);
                }
            }

            @Override
            public void onFailure(Call<List<Machine>> call, Throwable t) {

            }
        });

    }

}
