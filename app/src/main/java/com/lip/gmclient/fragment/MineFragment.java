package com.lip.gmclient.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.activity.AboutUsActiity;
import com.lip.gmclient.activity.BugResponseActivity;
import com.lip.gmclient.activity.FlowerRecoActivity;
import com.lip.gmclient.activity.WeatherActivity;
import com.lip.gmclient.base.GlideApp;
import com.lip.gmclient.base.LoginActivity;
import com.lip.gmclient.domain.UserBean;
import com.lip.gmclient.utils.Constant;
import com.lip.gmclient.utils.SharedPreferencesUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.w3c.dom.Text;

public class MineFragment extends Fragment {
    public Activity context;

    public ImageView headImageView;
    public TextView nameTextView;
    public TextView phoneTextView;

    public LinearLayout bugLayout;
    public LinearLayout aboutusLayout;
    public LinearLayout logoutLayout;
    public RelativeLayout taskLayout;
    public RelativeLayout weatherLayout;
    public LinearLayout flowerrecoLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine,container,false);
        headImageView=(ImageView)view.findViewById(R.id.fragment_mine_headimg);
        nameTextView=(TextView)view.findViewById(R.id.fragment_mine_username);
        phoneTextView=(TextView)view.findViewById(R.id.fragment_mine_phone);
        bugLayout=(LinearLayout)view.findViewById(R.id.fragment_mine_layout_bug);
        aboutusLayout=(LinearLayout)view.findViewById(R.id.fragment_mine_layout_aboutus);
        logoutLayout=(LinearLayout)view.findViewById(R.id.fragment_mine_layout_logout);
        flowerrecoLayout=view.findViewById(R.id.fragment_mine_layout_flowerreco);
        weatherLayout=view.findViewById(R.id.fragment_mine_weather);

        bugLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBugResponse(v);
            }
        });
        aboutusLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAboutUs(v);
            }
        });
        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogOut(v);
            }
        });
        weatherLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickWeather(v);
            }
        });
        flowerrecoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickFlowerReco(v);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData(){

        String uid;
        uid= (String)SharedPreferencesUtil.getParam(context,Constant.USERID,"15061883391");

        OkGo.<String>post(Constant.URL_USERINFO)
                .params("uid",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson=new Gson();
                        UserBean userBean=gson.fromJson(response.body(),UserBean.class);

                        nameTextView.setText(userBean.getData().getNickname());
                        phoneTextView.setText(userBean.getData().getUid());
                        GlideApp.with(context)
                                .load(userBean.getData().getAvatar())
                                .placeholder(R.drawable.mine_head_default)
                                .fitCenter()
                                .into(headImageView);
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.e(Constant.TAG,response.getException().getMessage());
                    }
                });
    }


    // 打开意见反馈
    private void onClickBugResponse(View v){
        Intent intent=new Intent(context, BugResponseActivity.class);
        startActivity(intent);
    }

    // 打开关于我们
    private void onClickAboutUs(View v){
        Intent intent=new Intent(context, AboutUsActiity.class);
        startActivity(intent);
    }

    // 退出登陆
    private void onClickLogOut(View v){
        // 删除保留在本地的数据
        SharedPreferencesUtil.setParam(context,Constant.ACCESSTOKEN,"NOTOKEN");
        SharedPreferencesUtil.setParam(context,Constant.PRIORITY,2);

        Intent intent=new Intent(context, LoginActivity.class);
        startActivity(intent);
    }

    // 打开天气
    private void onClickWeather(View v){
        Intent intent=new Intent(context, WeatherActivity.class);
        startActivity(intent);
    }

    // 打开识花
    private void onClickFlowerReco(View v){
        Intent intent=new Intent(context, FlowerRecoActivity.class);
        startActivity(intent);
    }
}
