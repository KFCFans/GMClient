package com.lip.gmclient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lip.gmclient.R;

public class FlowerRecoResultActivity extends AppCompatActivity {

    private String percent;
    private String name;

    private ImageView imageView;
    private TextView tv_percent;
    private TextView tv_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowerreco_result);
        imageView=findViewById(R.id.activity_flowerreco_result_imageview);
        tv_percent=findViewById(R.id.activity_flowerreco_result_percent);
        tv_name=findViewById(R.id.activity_flowerreco_result_name);
    }

    public void back(View v){
        finish();
    }
}
