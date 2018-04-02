package com.lip.gmclient.activity;

import android.content.Context;
import android.os.Bundle;
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
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class BugResponseActivity extends AppCompatActivity {

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
}
