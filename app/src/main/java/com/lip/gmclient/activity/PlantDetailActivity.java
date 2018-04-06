package com.lip.gmclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lip.gmclient.R;
import com.lip.gmclient.base.GlideApp;
import com.lip.gmclient.domain.PlantListBean;

public class PlantDetailActivity extends AppCompatActivity {

    public ImageView imageView;
    public TextView tv_plantname;
    public TextView tv_plantsname;
    public TextView tv_plantdetail;

    private String plantname;
    private String plantsname;
    private String plantdetail;
    private String plantimg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_plantdetail);
        imageView=(ImageView)findViewById(R.id.activity_plantdetail_headimg);
        tv_plantname=(TextView)findViewById(R.id.activity_plantdetail_plantname);
        tv_plantsname=(TextView)findViewById(R.id.activity_plantdetail_plantsname);
        tv_plantdetail=(TextView)findViewById(R.id.activity_plantdetail_plantdetail);

        tv_plantname.setText(plantname);
        tv_plantsname.setText(plantsname);
        tv_plantdetail.setText(plantdetail);
        GlideApp.with(this)
                .load(plantimg)
                .placeholder(R.drawable.task_item_default)
                .fitCenter()
                .into(imageView);
    }

    public void getData() {
        Bundle data=getIntent().getBundleExtra("data");
        plantname=data.getString("name");
        plantsname=data.getString("sname");
        plantdetail=data.getString("detail");
        plantimg=data.getString("img");
    }

    public void back(View v){
        finish();
    }
}
