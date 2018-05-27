package com.lip.gmclient.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.base.GlideApp;
import com.lip.gmclient.domain.ResponseBean;
import com.lip.gmclient.domain.UserBean;
import com.lip.gmclient.utils.Constant;
import com.lip.gmclient.utils.SharedPreferencesUtil;
import com.lip.gmclient.utils.TakePictureManager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.List;

public class BugResponseActivity extends AppCompatActivity {

    private TakePictureManager takePictureManager;
    public EditText editText;
    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_bugresponse);
        editText=(EditText)findViewById(R.id.activity_bugresponse_edittext);
        takePictureManager=new TakePictureManager(this);
        takePictureManager.setTakePictureCallBackListener(new TakePictureManager.takePictureCallBackListener() {
            @Override
            public void successful(boolean isTailor, File outFile, Uri filePath) {

            }

            @Override
            public void failed(int errorCode, List<String> deniedPermissions) {

            }
        });
    }

    public void back(View view){
        finish();
    }

    public void commitBugResponse(View view){

        String uid;
        String feedbackinfo;
        uid= (String) SharedPreferencesUtil.getParam(this,Constant.USERID,"15061883391");
        feedbackinfo=editText.getText().toString();

        OkGo.<String>post(Constant.URL_FEEDBACK)
                .params("uid",uid)
                .params("feedbackinfo",feedbackinfo)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson=new Gson();
                        ResponseBean responseBean=gson.fromJson(response.body(),ResponseBean.class);
                        if(responseBean.getStatus()==200){
                            Toast.makeText(context,"反馈成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Toast.makeText(context,"网络错误",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePictureManager.attachToActivityForResult(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        takePictureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
