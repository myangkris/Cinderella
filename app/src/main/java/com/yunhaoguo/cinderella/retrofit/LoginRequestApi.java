package com.yunhaoguo.cinderella.retrofit;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella.retrofit
 * 文件名:     LoginRequestApi
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/27 4:14 PM
 * 描述:      TODO
 */


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginRequestApi {

    @GET("url")
    Call<String> getMachinesStates(@Query("userId") int userId,
                                          @Query("password") int password);
}
