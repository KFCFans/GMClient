package com.lip.gmclient.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.activity.TaskDetailActivity;
import com.lip.gmclient.domain.TaskBean;
import com.lip.gmclient.utils.Constant;
import com.lip.gmclient.adapter.*;
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

public class TaskFragment extends Fragment implements AdapterView.OnItemClickListener,RadioGroup.OnCheckedChangeListener {

    private Activity context;
    private ListView listView;
    public TaskBean orginBean=null;
    public TaskBean taskBean=null;

    private Boolean netFinished=false;
    private SmartRefreshLayout refreshLayout;
    private SegmentedGroup segmentedGroup;
    private int taskType=-1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_task,container,false);
        listView=(ListView)view.findViewById(R.id.fragment_task_listview);
        listView.setOnItemClickListener(this);
        refreshLayout=(SmartRefreshLayout)view.findViewById(R.id.fragment_task_refresh);
        segmentedGroup=(SegmentedGroup)view.findViewById(R.id.fragment_task_segmentgroup);
        segmentedGroup.setOnCheckedChangeListener(this);

        // 关闭加载更多功能
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                initData();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

        String uid;
        uid= (String) SharedPreferencesUtil.getParam(context,Constant.USERID,"15061883391");

        // 初始化taskbean
        taskBean=new TaskBean();
        taskBean.data=new ArrayList<>();

        OkGo.<String>post(Constant.URL_TASKLIST)
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent=new Intent(context, TaskDetailActivity.class);
        Bundle data=new Bundle();
        TaskBean bean=null;
        if(taskType==-1){
            bean=orginBean;
        }else{
            bean=taskBean;
        }

        data.putString("name",bean.getData().get(position).getTname());
        data.putString("stime", DateUtil.getDateToString(bean.getData().get(position).getStime(),"yyyy年MM月dd日"));
        data.putString("etime",DateUtil.getDateToString(bean.getData().get(position).getEtime(),"yyyy年MM月dd日"));
        data.putString("detail",bean.getData().get(position).getTdetail());
        data.putString("imgurl",bean.getData().get(position).getTpic());

        data.putInt("type",bean.getData().get(position).getRtype());
        data.putInt("tid",bean.getData().get(position).getTid());
        data.putInt("aid",bean.getData().get(position).getAid());
        data.putInt("pid",bean.getData().get(position).getPid());
        data.putInt("iid",bean.getData().get(position).getIid());
        intent.putExtra("data",data);

        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.fragment_task_radiobtn_all:
                dealDataByType(-1);
                break;
            case R.id.fragment_task_radiobtn_save:
                dealDataByType(0);
                break;
            case R.id.fragment_task_radiobtn_replace:
                dealDataByType(1);
                break;
            case R.id.fragment_task_radiobtn_add:
                dealDataByType(2);
                break;
            case R.id.fragment_task_radiobtn_del:
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
}
