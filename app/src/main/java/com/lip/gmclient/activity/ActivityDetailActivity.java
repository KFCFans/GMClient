package com.lip.gmclient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lip.gmclient.R;
import com.lip.gmclient.base.GlideApp;
import com.lip.gmclient.utils.Constant;

public class ActivityDetailActivity extends AppCompatActivity {

    public ImageView imageView;
    public TextView tv_name;
    public TextView tv_place;
    public TextView tv_stime;
    public TextView tv_etime;
    public TextView tv_detail;

    private String imgurl;
    private String avname;
    private String avplace;
    private String avstime;
    private String avetime;
    private String avdetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }


    private void initData() {

        Bundle data=getIntent().getBundleExtra("data");
        avname=data.getString("avname");
        avdetail=data.getString("avdetail");
        avplace=data.getString("avplace");
        avstime=data.getString("avstime");
        avetime=data.getString("avetime");
        imgurl=data.getString("imgurl");
    }

    private void initView() {
        setContentView(R.layout.activity_activity_detail);
        imageView=findViewById(R.id.activity_detail_backimage);
        tv_name= findViewById(R.id.activity_detail_name);
        tv_place= findViewById(R.id.activity_detail_place);
        tv_stime=findViewById(R.id.activity_detail_avstime);
        tv_etime=findViewById(R.id.activity_detail_avetime);
        tv_detail=findViewById(R.id.activity_detail_avdetail);

        // 赋值
        tv_name.setText(avname);
        tv_place.setText(avplace);
        tv_stime.setText(avstime);
        tv_etime.setText(avetime);
        tv_detail.setText(avdetail);

        String final_url= Constant.URL_IMGHEAD+"/activity/"+imgurl;
        GlideApp.with(this)
                .load(final_url)
                .placeholder(R.drawable.mine_background_default)
                .fitCenter()
                .into(imageView);

    }

    public void back(View view){
        finish();
    }
}
