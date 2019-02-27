package com.yunhaoguo.cinderella.retrofit;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella.retrofit
 * 文件名:     RegisterRequestApi
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/27 4:16 PM
 * 描述:      TODO
 */


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterRequestApi {

    @POST("url")
    Call<String> getMachinesStates(@Body int userId, @Body int password);
}
