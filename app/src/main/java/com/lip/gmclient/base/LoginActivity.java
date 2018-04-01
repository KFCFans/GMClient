package com.lip.gmclient.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lip.gmclient.R;
import com.lip.gmclient.utils.Constant;

public class LoginActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private Button bt_login;
    private  Button bt_forgetpwd;
    private  Button bt_register;

    private String username;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView(){
        setContentView(R.layout.activity_login);

        et_username=(EditText) findViewById(R.id.activity_login_username);
        et_password=(EditText) findViewById(R.id.activity_login_password);
        bt_login=(Button)findViewById(R.id.activity_login_loginbtn);
        bt_forgetpwd=(Button)findViewById(R.id.activity_login_forgetbtn);
        bt_register=(Button)findViewById(R.id.activity_login_register);

    }

    /**
     * 登陆监听
     */
    public void onClickLoginBtn(View view){
        username=et_username.getText().toString();
        password=et_password.getText().toString();

    }

    /**
     * 忘记密码监听
     */
    public void onClickForgetBtn(View view){

    }

    /**
     * 注册监听
     */
    public void onClickRegisterBtn(View view){

    }
}
