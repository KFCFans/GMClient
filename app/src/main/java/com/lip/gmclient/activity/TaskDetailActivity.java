package com.lip.gmclient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lip.gmclient.R;
import com.lip.gmclient.base.GlideApp;

import org.w3c.dom.Text;

public class TaskDetailActivity extends AppCompatActivity {

    public ImageView imageView;
    public TextView tv_title;
    public TextView tv_starttime;
    public TextView tv_endtime;
    public TextView tv_detail;

    private String title;
    private String starttime;
    private String endtime;
    private String detail;
    private String imgurl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_taskdetail);
        imageView=(ImageView)findViewById(R.id.activity_taskdetail_headimg);
        tv_title=(TextView)findViewById(R.id.activity_taskdetail_taskname);
        tv_starttime=(TextView)findViewById(R.id.activity_taskdetail_starttime);
        tv_endtime=(TextView)findViewById(R.id.activity_taskdetail_endtime);
        tv_detail=(TextView)findViewById(R.id.activity_taskdetail_detail);

        tv_title.setText(title);
        tv_detail.setText(detail);
        tv_endtime.setText(endtime);
        tv_starttime.setText(starttime);
        GlideApp.with(this)
                .load(imgurl)
                .placeholder(R.drawable.task_item_default)
                .fitCenter()
                .into(imageView);
    }

    private void initData() {

    }

    public void back(View view){
        finish();
    }

    public void finishedTask(View view){

    }
}
