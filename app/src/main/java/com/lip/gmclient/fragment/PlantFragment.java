package com.lip.gmclient.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.activity.PlantDetailActivity;
import com.lip.gmclient.adapter.PlantRecyclerViewAdapter;
import com.lip.gmclient.adapter.TaskListViewAdapter;
import com.lip.gmclient.domain.PlantListBean;
import com.lip.gmclient.domain.TaskBean;
import com.lip.gmclient.utils.Constant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class PlantFragment extends Fragment{

    private Activity context;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_plant,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.fragment_plant_recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData(){

        OkGo.<String>get(Constant.URL_PLANTLIST)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson=new Gson();
                        final PlantListBean plantListBean=gson.fromJson(response.body(),PlantListBean.class);
                        PlantRecyclerViewAdapter plantRecyclerViewAdapter=new PlantRecyclerViewAdapter(context,plantListBean);
                        recyclerView.setAdapter(plantRecyclerViewAdapter);
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                        plantRecyclerViewAdapter.setOnItemClickListener(new PlantRecyclerViewAdapter.OnItemClickListener() {
                            @Override
                            public void onClick(int position) {

                                Intent intent=new Intent(context,PlantDetailActivity.class);
                                Bundle data=new Bundle();
                                data.putString("name",plantListBean.getData().get(position).getPname());
                                data.putString("sname",plantListBean.getData().get(position).getPsname());
                                data.putString("detail",plantListBean.getData().get(position).getPdetail());
                                data.putString("img",plantListBean.getData().get(position).getPimg());

                                data.putString("lhxg",plantListBean.getData().get(position).getPlhxg());
                                data.putString("xyfb",plantListBean.getData().get(position).getPxyfb());
                                data.putString("type",plantListBean.getData().get(position).getPtype());
                                intent.putExtra("data",data);
                                startActivity(intent);
                            }
                        });
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.e(Constant.TAG,response.getException().getMessage());
                    }
                });
    }
}
