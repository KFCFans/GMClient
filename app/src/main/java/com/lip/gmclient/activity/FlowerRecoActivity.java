package com.lip.gmclient.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.domain.TaskBean;
import com.lip.gmclient.utils.Constant;
import com.lip.gmclient.utils.Md5Util;
import com.lip.gmclient.utils.TakePictureManager;
import com.lip.gmclient.utils.TencentAIUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FlowerRecoActivity extends AppCompatActivity {

    private TakePictureManager takePictureManager;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowerreco);
        context=this;
        takePictureManager=new TakePictureManager(this);
        takePictureManager.setTakePictureCallBackListener(new TakePictureManager.takePictureCallBackListener() {
            @Override
            public void successful(boolean isTailor, File outFile, Uri filePath) {
                TreeMap<String,String> map=new TreeMap<>();
                String image= TencentAIUtil.fileToBase64(outFile);
                String time_stamp = System.currentTimeMillis() / 1000 + "";
                String nonce_str=TencentAIUtil.getRandomString(16);
                String sign=null;
                map.put("image",image);
                map.put("app_id",Constant.Tencent_AppID);
                map.put("time_stamp",time_stamp);
                map.put("scene","2");
                map.put("nonce_str",nonce_str);
                try{
                     sign=TencentAIUtil.generateAppSign(map);
                     map.put("sign",sign);
                }catch (Exception e){
                    Toast.makeText(context,"生成密钥失败",Toast.LENGTH_SHORT).show();
                }
                // 网络请求
                OkGo.<String>post(Constant.Tencent_API)
                        .params(map)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {

                            }

                            @Override
                            public void onError(Response<String> response) {

                            }
                        });
            }

            @Override
            public void failed(int errorCode, List<String> deniedPermissions) {
                Toast.makeText(context,"请提供访问权限！",Toast.LENGTH_SHORT).show();
            }
        });
    }


    //把本地的onActivityResult()方法回调绑定到对象
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePictureManager.attachToActivityForResult(requestCode, resultCode, data);
    }
    //onRequestPermissionsResult()方法权限回调绑定到对象
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        takePictureManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void back(View v){finish();}

    //　拍照
    public void onClickCamera(View v){
        takePictureManager.startTakeWayByCarema();
    }

    // 照片图库
    public void onClickAlbum(View v){
        takePictureManager.startTakeWayByAlbum();
    }

    // OKGO 调用腾讯AI接口
    private void flowerRecoByTencent(){

    }
}
