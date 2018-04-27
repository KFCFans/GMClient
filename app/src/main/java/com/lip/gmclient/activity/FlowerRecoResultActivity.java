package com.lip.gmclient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lip.gmclient.R;
import com.lip.gmclient.base.GlideApp;

public class FlowerRecoResultActivity extends AppCompatActivity {

    private String percent;
    private String name;
    private String filepath;

    private ImageView imageView;
    private TextView tv_percent;
    private TextView tv_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    public void back(View v){
        finish();
    }
    private void initData(){
        Bundle data=getIntent().getBundleExtra("data");
        filepath=data.getString("filepath");
        name=data.getString("name");
        percent=data.getString("percent");
    }

    private void initView(){
        setContentView(R.layout.activity_flowerreco_result);
        imageView=findViewById(R.id.activity_flowerreco_result_imageview);
        tv_percent=findViewById(R.id.activity_flowerreco_result_percent);
        tv_name=findViewById(R.id.activity_flowerreco_result_name);

        tv_name.setText(name);
        tv_percent.setText(percent);
        GlideApp.with(this).load(filepath).into(imageView);
    }
}
