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

import info.hoang8f.android.segmented.SegmentedGroup;

public class TaskFragment extends Fragment implements AdapterView.OnItemClickListener,RadioGroup.OnCheckedChangeListener {

    private Activity context;
    private ListView listView;
    public TaskBean taskBean=null;

    private SmartRefreshLayout refreshLayout;
    private SegmentedGroup segmentedGroup;

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

        OkGo.<String>post(Constant.URL_TASKLIST)
                .params("uid",uid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson=new Gson();
                        taskBean=gson.fromJson(response.body(),TaskBean.class);
                        listView.setAdapter(new TaskListViewAdapter(context,taskBean));
                        refreshLayout.finishRefresh();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.e(Constant.TAG,response.getException().getMessage());
                        refreshLayout.finishRefresh();
                        Toast.makeText(context,"网络错误！",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(taskBean==null) return;
        Intent intent=new Intent(context, TaskDetailActivity.class);
        Bundle data=new Bundle();
        data.putString("name",taskBean.getData().get(position).getTname());
        data.putString("stime", DateUtil.getDateToString(taskBean.getData().get(position).getStime(),"yyyy年MM月dd日"));
        data.putString("etime",DateUtil.getDateToString(taskBean.getData().get(position).getEtime(),"yyyy年MM月dd日"));
        data.putString("detail",taskBean.getData().get(position).getTdetail());
        data.putString("imgurl",taskBean.getData().get(position).getTpic());
        data.putInt("type",taskBean.getData().get(position).getRtype());
        data.putInt("tid",taskBean.getData().get(position).getTid());
        data.putInt("aid",taskBean.getData().get(position).getAid());
        data.putInt("pid",taskBean.getData().get(position).getPid());
        data.putInt("iid",taskBean.getData().get(position).getIid());
        intent.putExtra("data",data);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.fragment_task_radiobtn_all:
                break;
            case R.id.fragment_task_radiobtn_save:break;
            case R.id.fragment_task_radiobtn_replace:break;
            case R.id.fragment_task_radiobtn_add:break;
            case R.id.fragment_task_radiobtn_del:break;
        }
    }
}
