package com.lip.gmclient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lip.gmclient.R;
import com.lip.gmclient.base.GlideApp;
import com.lip.gmclient.utils.Constant;

public class PlantDetailActivity extends AppCompatActivity {

    public ImageView imageView;
    public TextView tv_plantname;
    public TextView tv_plantsname;
    public TextView tv_plantdetail;
    public TextView tv_plantlhxg;
    public TextView tv_plantxyfb;
    public TextView tv_planttype;

    private String plantname;
    private String plantsname;
    private String plantdetail;
    private String plantimg;
    private String plantlhxg;
    private String plantxyfb;
    private String planttype;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_plantdetail);
        imageView=findViewById(R.id.activity_plantdetail_headimg);
        tv_plantname=findViewById(R.id.activity_plantdetail_plantname);
        tv_plantsname=findViewById(R.id.activity_plantdetail_plantsname);
        tv_plantdetail=findViewById(R.id.activity_plantdetail_plantdetail);
        tv_plantlhxg=findViewById(R.id.activity_plantdetail_plantlhxg);
        tv_planttype=findViewById(R.id.activity_plantdetail_planttype);
        tv_plantxyfb=findViewById(R.id.activity_plantdetail_plantxyfb);

        tv_plantname.setText(plantname);
        tv_plantsname.setText(plantsname);
        tv_plantdetail.setText(plantdetail);
        tv_plantlhxg.setText(plantlhxg);
        tv_plantxyfb.setText(plantxyfb);
        tv_planttype.setText(planttype);

        String final_url= Constant.URL_IMGHEAD+"/plant/"+plantimg+".jpg";
        GlideApp.with(this)
                .load(final_url)
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

        plantlhxg=data.getString("lhxg");
        plantxyfb=data.getString("xyfb");
        planttype=data.getString("type");

    }

    public void back(View v){
        finish();
    }
}
