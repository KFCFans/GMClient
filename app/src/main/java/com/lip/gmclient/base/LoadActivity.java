package com.lip.gmclient.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.domain.ResponseBean;
import com.lip.gmclient.utils.Constant;
import com.lip.gmclient.utils.SharedPreferencesUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.victor.loading.newton.NewtonCradleLoading;

public class LoadActivity extends AppCompatActivity{

    private String NOTOKEN="NOTOKEN";
    public Context context;
    public NewtonCradleLoading newtonCradleLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_load);
        String token= (String) SharedPreferencesUtil.getParam(this, Constant.ACCESSTOKEN,NOTOKEN);
        if(token.equals(NOTOKEN)){
            Intent intent=new Intent();
            intent.setClass(this,LoginActivity.class);
            startActivity(intent);
        }else{
            newtonCradleLoading=(NewtonCradleLoading)findViewById(R.id.activity_load_loading);
            newtonCradleLoading.start();

            OkGo.<String>post(Constant.URL_TOKENLOGIN)
                    .params("token",token)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Gson gson=new Gson();
                            ResponseBean responseBean=gson.fromJson(response.body(),ResponseBean.class);

                            int status=responseBean.getStatus();
                            if(status==200){
                                //登陆成功
                                newtonCradleLoading.stop();
                                Intent intent=new Intent();
                                intent.setClass(context,MainActivity.class);
                                startActivity(intent);

                            }else if(status==400){
                                // Token错误
                                Toast.makeText(context,"身份信息失效，请重新登陆",Toast.LENGTH_SHORT).show();
                                SharedPreferencesUtil.setParam(context,Constant.ACCESSTOKEN,NOTOKEN);
                            }else{
                                Toast.makeText(context,"服务器错误，请联系管理员！",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            // 用户尚未注册

                        }
                    });
        }

    }
}
