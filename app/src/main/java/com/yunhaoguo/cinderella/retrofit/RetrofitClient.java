package com.yunhaoguo.cinderella.retrofit;
/*
 * 项目名:     Cinderella
 * 包名:      com.yunhaoguo.cinderella.retrofit
 * 文件名:     RetrofitClient
 * 创建者:     yunhaoguo
 * 创建时间:    2019/2/27 3:55 PM
 * 描述:      TODO
 */


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "";
    private volatile static Retrofit instance;

    private RetrofitClient() {

    }

    public static Retrofit getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return instance;
    }
}
