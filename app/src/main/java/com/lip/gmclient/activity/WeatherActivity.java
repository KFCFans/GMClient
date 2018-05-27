package com.lip.gmclient.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lip.gmclient.R;
import com.lip.gmclient.base.GlideApp;
import com.lip.gmclient.domain.WeatherBean;
import com.lip.gmclient.utils.Constant;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class WeatherActivity extends AppCompatActivity {

    private Context context;
    private WeatherBean weatherBean;

    private ImageView weatherImageView;
    private TextView tv_updatetime;
    private TextView tv_windpower;
    private TextView tv_winddirection;
    private TextView tv_weather;
    private TextView tv_tempeture;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        setContentView(R.layout.activity_weather);
        context=this;

        weatherImageView=findViewById(R.id.activity_weather_img);
        tv_updatetime=findViewById(R.id.activity_weather_updatetime);
        tv_winddirection=findViewById(R.id.activity_weather_wind_direction);
        tv_windpower=findViewById(R.id.activity_weather_wind_power);
        tv_weather=findViewById(R.id.activity_weather_condition_tv);
        tv_tempeture=findViewById(R.id.activity_weather_tempeture);
    }

    public void back(View v){
        finish();
    }

    private void initData(){

        // 加载天气数据
        OkGo.<String>get(Constant.HEWEATHER_URL)
                .params("location",Constant.HEWEATHER_LOCATION)
                .params("key",Constant.HEWEATHER_KEY)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson=new Gson();
                        weatherBean=gson.fromJson(response.body(),WeatherBean.class);
                        tv_updatetime.setText(weatherBean.getUpdateTime());
                        tv_winddirection.setText(weatherBean.getWindDirection());
                        tv_windpower.setText(weatherBean.getWindPower());
                        tv_weather.setText(weatherBean.getWeather());
                        tv_tempeture.setText(weatherBean.getTempeture());
                        GlideApp.with(context)
                                .load(weatherBean.getWeatherImgURL())
                                .placeholder(R.drawable.activity_weather_default)
                                .into(weatherImageView);
                    }
                    @Override
                    public void onError(Response<String> response) {
                        Log.e(Constant.TAG,response.getException().getMessage());
                        Toast.makeText(context,"网络错误！",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
