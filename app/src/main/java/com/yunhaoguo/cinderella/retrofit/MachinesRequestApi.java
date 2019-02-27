package com.yunhaoguo.cinderella.retrofit;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella.retrofit
 * 文件名:     MachinesRequestApi
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/27 4:03 PM
 * 描述:      TODO
 */


import com.yunhaoguo.cinderella.entity.Machine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MachinesRequestApi {

    @GET("url")
    Call<List<Machine>> getMachinesStates(@Query("userId") int userId,
                                         @Query("machineId") int machineId,
                                         @Query("washingDuration") int washingDuration);
}
