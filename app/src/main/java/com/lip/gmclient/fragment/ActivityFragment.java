package com.lip.gmclient.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lip.gmclient.R;
import com.lip.gmclient.adapter.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {

    public Activity context;

    private Banner banner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_activity,container,false);
        banner=(Banner) view.findViewById(R.id.fragment_activity_banner);
        banner.setImageLoader(new GlideImageLoader());
        //设置图片加载集合(网络加载)
        List<String> imageArray=new ArrayList<>();
        imageArray.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524310255028&di=4d43cbf42f7923e134aa7daee56f56bb&imgtype=0&src=http%3A%2F%2Fpic62.nipic.com%2Ffile%2F20150319%2F20593754_104525140961_2.jpg");
        imageArray.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524310255007&di=59513cb9f5d698655335140ef5353f16&imgtype=0&src=http%3A%2F%2Fbpic.ooopic.com%2F16%2F53%2F21%2F16532165-91094019202e021aa768b30314b7dc15.jpg");
        imageArray.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524310255005&di=14666de35820f221636644bd51448274&imgtype=0&src=http%3A%2F%2Fwww.gardensmuseum.cn%2Fupload%2FActiveImg%2FACT_2014327173451513.jpg");

        banner.setImages(imageArray);
        banner.start();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

    }
}
