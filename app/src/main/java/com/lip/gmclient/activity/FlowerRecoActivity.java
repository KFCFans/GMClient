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
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.domain.FlowerRecoBean;
import com.lip.gmclient.domain.TaskBean;
import com.lip.gmclient.utils.Constant;
import com.lip.gmclient.utils.Md5Util;
import com.lip.gmclient.utils.TakePictureManager;
import com.lip.gmclient.utils.TencentAIUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.victor.loading.newton.NewtonCradleLoading;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FlowerRecoActivity extends AppCompatActivity {

    private TakePictureManager takePictureManager;
    private NewtonCradleLoading newtonCradleLoading;
    private ImageButton cameraBtn;
    private ImageButton albumBtn;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowerreco);
        context=this;
        newtonCradleLoading=findViewById(R.id.activity_flowerreco_loading);
        cameraBtn=findViewById(R.id.activity_flowerreco_camera_btn);
        albumBtn=findViewById(R.id.activity_flowerreco_album_btn);
        takePictureManager=new TakePictureManager(this);
        takePictureManager.setTakePictureCallBackListener(new TakePictureManager.takePictureCallBackListener() {
            @Override
            public void successful(boolean isTailor, File outFile, final Uri filePath) {
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
                newtonCradleLoading.setVisibility(View.VISIBLE);
                newtonCradleLoading.setLoadingColor(R.color.color3);
                newtonCradleLoading.start();
                disableBtn();
                OkGo.<String>post(Constant.Tencent_API)
                        .params(map)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Gson gson=new Gson();
                                FlowerRecoBean  bean=gson.fromJson(response.body(),FlowerRecoBean.class);
                                if(bean.getRet()!=0){
                                    if(bean.getRet()==16460){
                                        Toast.makeText(context,"无匹配项！"+bean.getMsg(),Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(context,"接口异常！"+bean.getMsg(),Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    String flower_name=bean.getData().getTag_list().get(0).getLabel_name();
                                    Double flower_probability=bean.getData().getTag_list().get(0).getLabel_confd()*100;

                                    Bundle data=new Bundle();
                                    data.putString("filepath",filePath.toString());
                                    data.putString("name",flower_name);
                                    data.putString("percent",String.format("%.2f", flower_probability)+"%");

                                    Intent intent=new Intent(context,FlowerRecoResultActivity.class);
                                    intent.putExtra("data",data);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFinish() {
                                // 关闭加载动画，重启按钮
                                newtonCradleLoading.stop();
                                newtonCradleLoading.setVisibility(View.INVISIBLE);
                                enableBtn();
                            }

                            @Override
                            public void onError(Response<String> response) {
                                Toast.makeText(context,"网络异常！",Toast.LENGTH_SHORT).show();
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

    // 关闭按钮
    private void disableBtn(){
        albumBtn.setEnabled(false);
        cameraBtn.setEnabled(false);
    }

    // 打开按钮
    private void enableBtn(){
        albumBtn.setEnabled(true);
        cameraBtn.setEnabled(true);
    }
}
