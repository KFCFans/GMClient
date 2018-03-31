package com.lip.gmclient.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lip.gmclient.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    private int FirstSelectedPosition =0;
    private BottomNavigationBar bottomNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView(){
        setContentView(R.layout.activity_main);
        bottomNavigationBar=(BottomNavigationBar) findViewById(R.id.main_bottomnav);

        // 初始化底部导航栏
        bottomNavigationBar
                .setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_DEFAULT)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setActiveColor("#FF107FFD")
                .setInActiveColor("#e9e6e6")
                .setBarBackgroundColor("#1ccbae");
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.nav_task,"任务"))
                .addItem(new BottomNavigationItem(R.drawable.nav_plant,"植物"))
                .addItem(new BottomNavigationItem(R.drawable.nav_mine,"我的"))
                .setFirstSelectedPosition(FirstSelectedPosition)
                .initialise();
        setDefaultFragment();

    }

    private void setDefaultFragment(){

    }

    @Override
    public void onTabSelected(int position) {

    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
