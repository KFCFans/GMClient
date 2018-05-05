package com.lip.gmclient.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.adapter.TaskListViewAdapter;
import com.lip.gmclient.domain.TaskBean;
import com.lip.gmclient.utils.Constant;
import com.lip.gmclient.utils.DateUtil;
import com.lip.gmclient.utils.SharedPreferencesUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import info.hoang8f.android.segmented.SegmentedGroup;

public class HistoryTaskActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private Activity context;
    private ListView listView;
    public TaskBean orginBean=null;
    public TaskBean taskBean=null;

    private Boolean netFinished=false;
    private SmartRefreshLayout refreshLayout;
    private SegmentedGroup segmentedGroup;
    private int taskType=-1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        setContentView(R.layout.activity_historytask);

        listView=findViewById(R.id.activity_historytask_listview);
        refreshLayout=findViewById(R.id.activity_historytask_refresh);
        segmentedGroup=findViewById(R.id.activity_historytask_segmentgroup);
        segmentedGroup.setOnCheckedChangeListener(this);

        // 关闭加载更多功能
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });
        initData();
    }

    private void initData(){

        String uid;
        uid= (String) SharedPreferencesUtil.getParam(context, Constant.USERID,"15061883391");

        // 初始化taskbean
        taskBean=new TaskBean();
        taskBean.data=new ArrayList<>();

        OkGo.<String>post(Constant.URL_HISTORYTASKLIST)
                .params("uid",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        netFinished=true;
                        Gson gson=new Gson();
                        orginBean=gson.fromJson(response.body(),TaskBean.class);
                        dealDataByType(taskType);
                        refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        refreshLayout.finishRefresh();
                        Toast.makeText(context,"网络错误！",Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.activity_historytask_radiobtn_all:
                dealDataByType(-1);
                break;
            case R.id.activity_historytask_radiobtn_save:
                dealDataByType(0);
                break;
            case R.id.activity_historytask_radiobtn_replace:
                dealDataByType(1);
                break;
            case R.id.activity_historytask_radiobtn_add:
                dealDataByType(2);
                break;
            case R.id.activity_historytask_radiobtn_del:
                dealDataByType(3);
                break;
        }
    }

    // 任务类型，0为维护，1为移植，2为新增，3为删除  -1为全部数据
    public void dealDataByType(int type){
        taskType=type;

        if(!netFinished) return;

        if(type==-1){
            listView.setAdapter(new TaskListViewAdapter(context,orginBean));
            return;
        }
        taskBean.getData().clear();
        for(int i=0;i<orginBean.getData().size();i++){
            if(orginBean.getData().get(i).getRtype()==type){
                taskBean.getData().add(orginBean.getData().get(i));
            }
        }
        listView.setAdapter(new TaskListViewAdapter(context,taskBean));
    }

    public void back(View v){
        finish();
    }
}
