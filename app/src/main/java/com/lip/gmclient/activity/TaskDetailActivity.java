package com.lip.gmclient.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.adapter.TaskListViewAdapter;
import com.lip.gmclient.base.GlideApp;
import com.lip.gmclient.domain.ResponseBean;
import com.lip.gmclient.domain.TaskBean;
import com.lip.gmclient.utils.Constant;
import com.lip.gmclient.utils.SharedPreferencesUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

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

    private int taskType;
    private int tid;
    private int iid;
    private int pid;
    private int aid;

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

        String final_url=Constant.URL_IMGHEAD+"/task/"+imgurl;
        GlideApp.with(this)
                .load(final_url)
                .placeholder(R.drawable.task_item_default)
                .fitCenter()
                .into(imageView);
    }

    private void initData() {

        Bundle data=getIntent().getBundleExtra("data");
        title=data.getString("name");
        starttime=data.getString("stime");
        endtime=data.getString("etime");
        detail=data.getString("detail");
        imgurl=data.getString("imgurl");

        tid=data.getInt("tid");
        iid=data.getInt("iid");
        aid=data.getInt("aid");
        pid=data.getInt("pid");
        taskType=data.getInt("type");
    }

    public void back(View view){
        finish();
    }

    public void finishedTask(View view){

        String uid;
        uid= (String) SharedPreferencesUtil.getParam(this, Constant.USERID,"15061883391");

        /**
         * 任务类型，0为维护，1为移植，2为新增，3为删除
         */
        if(taskType==0){

            // 维护
            // 删除
            OkGo.<String>post(Constant.URL_FINISHMAITAINTASK)
                    .params("uid",uid)
                    .params("iid",iid)
                    .params("tid",tid)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            finish();
                        }
                        @Override
                        public void onError(Response<String> response) {
                            Log.e(Constant.TAG,response.getException().getMessage());
                        }
                    });

        }else if(taskType==1){

            // 移植
            OkGo.<String>post(Constant.URL_FINISHREPLACETASK)
                    .params("uid",uid)
                    .params("iid",iid)
                    .params("aid",aid)
                    .params("tid",tid)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            finish();
                        }
                        @Override
                        public void onError(Response<String> response) {
                            Log.e(Constant.TAG,response.getException().getMessage());
                        }
                    });

        }else if(taskType==2){

            // 新增

            //FIXME: 上传图片
            String picurl="android pic";
            OkGo.<String>post(Constant.URL_FINISHADDTASK)
                    .params("uid",uid)
                    .params("pid",pid)
                    .params("tid",tid)
                    .params("aid",aid)
                    .params("ipic",picurl)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            finish();
                        }
                        @Override
                        public void onError(Response<String> response) {
                            Log.e(Constant.TAG,response.getException().getMessage());
                        }
                    });

        }else{

            // 删除
            OkGo.<String>post(Constant.URL_FINISHDELTASK)
                    .params("uid",uid)
                    .params("iid",iid)
                    .params("tid",tid)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            finish();
                        }
                        @Override
                        public void onError(Response<String> response) {
                            Log.e(Constant.TAG,response.getException().getMessage());
                        }
                    });
        }

    }
}
