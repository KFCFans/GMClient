package com.lip.gmclient.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.adapter.ActivityListViewAdapter;
import com.lip.gmclient.adapter.GlideImageLoader;
import com.lip.gmclient.adapter.TaskListViewAdapter;
import com.lip.gmclient.domain.ActivityListBean;
import com.lip.gmclient.domain.TaskBean;
import com.lip.gmclient.domain.VPListBean;
import com.lip.gmclient.utils.Constant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {

    public Activity context;

    private Banner banner;
    private ListView listView;
    List<String> imageArray;

    public ActivityListBean activityListBean=null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_activity,container,false);
        listView=(ListView)view.findViewById(R.id.fragment_activity_listview);
        banner=(Banner) view.findViewById(R.id.fragment_activity_banner);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

        // 加载viewpager图片数据
        OkGo.<String>post(Constant.URL_VIEWPAGER)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson=new Gson();
                        VPListBean vpListBean=gson.fromJson(response.body(),VPListBean.class);
                        imageArray=new ArrayList<>();
                        for(int i=0;i<vpListBean.getData().size();i++){
                            String picurl=Constant.URL_IMGHEAD+"/viewpager/"+vpListBean.getData().get(i).getUrl()+".jpg";
                            imageArray.add(picurl);
                        }
                        banner.setImageLoader(new GlideImageLoader());
                        banner.setImages(imageArray);
                        banner.start();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.e(Constant.TAG,response.getException().getMessage());
                        Toast.makeText(context,"网络错误！",Toast.LENGTH_SHORT).show();
                    }
                });

        // 加载活动数据
        OkGo.<String>post(Constant.URL_ACTIVITYLIST)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        Gson gson=new Gson();
                        ActivityListBean activityListBean=gson.fromJson(response.body(),ActivityListBean.class);
                        listView.setAdapter(new ActivityListViewAdapter(context,activityListBean));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.e(Constant.TAG,response.getException().getMessage());

                        Toast.makeText(context,"网络错误！",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
