package com.lip.gmclient.base;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())
                .setRetryCount(3)
                .setCacheMode(CacheMode.DEFAULT);
    }
}
